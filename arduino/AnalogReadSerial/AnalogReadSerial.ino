/*
  AnalogReadSerial

  Reads an analog input on pin 0, prints the result to the Serial Monitor.
  Graphical representation is available using Serial Plotter (Tools > Serial Plotter menu).
  Attach the center pin of a potentiometer to pin A0, and the outside pins to +5V and ground.

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/AnalogReadSerial
*/


#include "Logger.h"

int led = 13;
unsigned char START_BYTE = 126;
unsigned char END_BYTE = 37;
unsigned char ESCAPE_BYTE = 63;
int rawDataLength = 100;
int readingIndex = 0;
int descapedDataLength = 0;
byte rawData[100];
byte descapedData[100];
byte incomingByte;
bool nextEscaped = false;
bool lastRound = false;
Logger logger(true);

enum State {
  START,
  LENGTH,
  READING,
  END
};

State readingState = State::START;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  Serial.setTimeout(100);
  pinMode(led, OUTPUT);

}

// the loop routine runs over and over again forever:
void loop() {

  if (Serial.available() > 0) {

    //logState();

    // read the incoming byte:
    incomingByte = Serial.read();

    // say what you got:
    // logger.log(incomingByte);

    switch (readingState) {

      case START:
        if (incomingByte == START_BYTE) {
          readingState = State::LENGTH;
        }
        break;

      case LENGTH:

        rawDataLength = (int) incomingByte;

        readingState = State::READING;

        break;

      case READING:

        if (nextEscaped) {

          nextEscaped = false;

        } else {

          if (incomingByte == END_BYTE) {

            readingState = State::END;
            lastRound = true;
            break;
          }
        }

        nextEscaped = checkEscaped(incomingByte);

        rawData[readingIndex] = incomingByte;
        readingIndex++;

        // logger.log(incomingByte);

        break;

      case END:
        // logger.logString("END");

        descapeData();

        if ((descapedData, DEC) == 10) {
          digitalWrite(led, HIGH);
          delay(1000);
          digitalWrite(led, LOW);

        }

        // logger.logString(String((char*) descapedData));

        // writeBytes();

        readingState = State::START;
        lastRound = false;
        readingIndex = 0;
        break;
    }
  }
}

bool checkEscaped(byte incomingByte) {
  return incomingByte == ESCAPE_BYTE;
}

void descapeData() {
  descapedDataLength = 0;

  for (int i = 0; i < rawDataLength; i++) {
    if (!checkEscaped(rawData[i])) {
      descapedDataLength++;
    }
  }

  int descapedDataIndex = 0;

  for (int i = 0; i < rawDataLength; i++) {
    if (!checkEscaped(rawData[i])) {
      descapedData[descapedDataIndex] = rawData[i];
      descapedDataIndex++;
    }
  }
}

void writeBytes() {
  for (int i = 0; i < descapedDataLength; i++) {
    logger.log(descapedData[i]);
  }
}


void logState() {
  String stateMsg = "";
  switch (readingState) {

    case START:
      stateMsg = "start";
      break;

    case LENGTH:
      stateMsg = "length";
      break;

    case READING:
      stateMsg = "reading";
      break;

    case END:
      stateMsg = "end";
      break;

  }
  logger.logString(stateMsg);
}
