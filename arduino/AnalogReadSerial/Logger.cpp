#include "Arduino.h"
#include "Logger.h"

Logger::Logger(bool write) {
  _write = write;
}

void Logger::log(byte msg) {
  if (_write && Serial.availableForWrite()) {
    Serial.write(msg);
  }
}

void Logger::logString(String msg) {
  if (_write && Serial.availableForWrite()) {
    Serial.print(msg);
  }
}

void Logger::logInt(int msg) {
  if (_write && Serial.availableForWrite()) {
    newLine();
    Serial.write(msg);
  }
}

void Logger::newLine() {
  if (_write && Serial.availableForWrite()) {
    Serial.println();
  }
}


void Logger::enable() {
  _write = true;
}

void Logger::disable() {
  _write = false;
}
