/*
  AnalogReadSerial

  Reads an analog input on pin 0, prints the result to the Serial Monitor.
  Graphical representation is available using Serial Plotter (Tools > Serial Plotter menu).
  Attach the center pin of a potentiometer to pin A0, and the outside pins to +5V and ground.

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/AnalogReadSerial
*/
#include "SerialTransfer.h"
SerialTransfer transfer;

int led = 13;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  Serial.setTimeout(100);
  transfer.begin(Serial);
  pinMode(led, OUTPUT);
}

// the loop routine runs over and over again forever:
void loop() {
  
  if (transfer.available() > 0) {
    int dataLength;
    dataLength = transfer.bytesRead;
    byte value[dataLength];
    
    for (int i = 0; i < dataLength; i++) {
      value[i] = transfer.rxBuff[i];
    }
    if (dataLength > 0 && value != NULL && value != "") {
      digitalWrite(led, HIGH);
      delay(5000);
      digitalWrite(led, LOW);
      delay(1000);   
    }
    
    for (int i = 0; i < dataLength; i++) {
        transfer.txBuff[i] = value[i];
    }
    transfer.txBuff[dataLength] = '/n';
    transfer.sendData(dataLength + 1);
  }
}
