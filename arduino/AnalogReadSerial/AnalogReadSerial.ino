/*
  AnalogReadSerial

  Reads an analog input on pin 0, prints the result to the Serial Monitor.
  Graphical representation is available using Serial Plotter (Tools > Serial Plotter menu).
  Attach the center pin of a potentiometer to pin A0, and the outside pins to +5V and ground.

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/AnalogReadSerial
*/

int led = 13;
unsigned char START_BYTE = 126;
unsigned char END_BYTE = 37;
unsigned char ESCAPE_BYTE = 63;
int rawDataLength = 0;
int readingIndex = 0;
int descapedDataLength = 0;
byte rawData[] = {};
byte descapedData[] = {};
byte incomingByte;
bool nextEscaped = false;

enum state {
  START,
  LENGTH,
  READING,
  END
};

state readingState = state::START;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  Serial.setTimeout(100);
}

// the loop routine runs over and over again forever:
void loop() {
  if (Serial.available()) {
    incomingByte = Serial.read();
  }

  switch (readingState) {
    case START:

      if (incomingByte && (incomingByte = START_BYTE)) {
        readingState = state::LENGTH;
      }
      break;

    case LENGTH:

      if (incomingByte) {
        rawDataLength = (int) incomingByte;

        rawData[rawDataLength];
        readingState = state::READING;
      }
      break;

    case READING:

      if (incomingByte) {
        if (nextEscaped) {
          rawData[readingIndex] = incomingByte;
          nextEscaped = false;
        } else {
          if (incomingByte = END_BYTE) {
            readingState = state::END;
            break;
          }
          nextEscaped = checkEscaped(incomingByte);
        }
        rawData[readingIndex] = incomingByte;
        readingIndex++;
      }
      break;

    case END:
      descapeData();
      break;
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

  descapedData[descapedDataLength];
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
    Serial.print(descapedData[i]);
  }
}
