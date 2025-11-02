package com.warmhouse.telemetryservice.repository;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import com.warmhouse.telemetryservice.entity.TelemetryData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TelemetryRepository {

    private final WriteApiBlocking writeApi;
    private final QueryApi queryApi;

    @Value("${influx.bucket}")
    private String bucket;

    @Value("${influx.org}")
    private String org;

    public TelemetryRepository(InfluxDBClient influxDBClient) {
        this.writeApi = influxDBClient.getWriteApiBlocking();
        this.queryApi = influxDBClient.getQueryApi();
    }

    public void save(TelemetryData data) {
        writeApi.writeMeasurement(WritePrecision.NS, data);
    }

    public List<TelemetryData> findByPeriodAndFilters(Instant from, Instant to, UUID deviceId, UUID moduleId) {
        String flux = String.format(
                "from(bucket:\"%s\") |> range(start: %s, stop: %s)",
                bucket, from, to);

        if (deviceId != null) {
            flux += String.format(" |> filter(fn: (r) => r.device_id == \"%s\")", deviceId);
        }
        if (moduleId != null) {
            flux += String.format(" |> filter(fn: (r) => r.module_id == \"%s\")", moduleId);
        }

        List<FluxTable> tables = queryApi.query(flux, org);
        List<TelemetryData> results = new ArrayList<>();

        for (FluxTable table : tables) {
            for (FluxRecord record : table.getRecords()) {
                TelemetryData data = new TelemetryData();
                data.setTelemetryId(UUID.fromString(record.getValueByKey("telemetry_id").toString()));
                data.setDeviceId(UUID.fromString(record.getValueByKey("device_id").toString()));
                data.setModuleId(UUID.fromString(record.getValueByKey("module_id").toString()));
                data.setType(record.getValueByKey("type").toString());
                data.setValue((Double) record.getValueByKey("value"));
                data.setTimestamp(record.getTime());
                results.add(data);
            }
        }

        return results;
    }
}