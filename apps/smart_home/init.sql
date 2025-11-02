-- Create the database if it doesn't exist
CREATE DATABASE smarthome;

-- Connect to the database
\c smarthome;

-- Create the sensors table
CREATE TABLE IF NOT EXISTS sensors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    location VARCHAR(100) NOT NULL,
    value FLOAT DEFAULT 0,
    unit VARCHAR(20),
    status VARCHAR(20) NOT NULL DEFAULT 'inactive',
    last_updated TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

-- Create indexes for common queries
CREATE INDEX IF NOT EXISTS idx_sensors_type ON sensors(type);
CREATE INDEX IF NOT EXISTS idx_sensors_location ON sensors(location);
CREATE INDEX IF NOT EXISTS idx_sensors_status ON sensors(status);


CREATE DATABASE device_db;

-- Connect to the database
\c device_db;

CREATE TABLE IF NOT EXISTS device_type (
                                           type_id UUID PRIMARY KEY,
                                           name VARCHAR(255) NOT NULL,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS device (
                                      device_id UUID PRIMARY KEY,
                                      house_id UUID NOT NULL,
                                      type_id UUID REFERENCES device_type(type_id) ON DELETE RESTRICT,
    serial_number VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    location VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX IF NOT EXISTS idx_device_house_id ON device (house_id);
CREATE INDEX IF NOT EXISTS idx_device_type_id ON device (type_id);

CREATE TABLE IF NOT EXISTS module (
                                      module_id UUID PRIMARY KEY,
                                      device_id UUID REFERENCES device(device_id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
    );


CREATE INDEX IF NOT EXISTS idx_module_device_id ON module (device_id);