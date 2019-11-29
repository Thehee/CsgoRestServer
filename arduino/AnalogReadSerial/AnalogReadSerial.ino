/*
  AnalogReadSerial

  Reads an analog input on pin 0, prints the result to the Serial Monitor.
  Graphical representation is available using Serial Plotter (Tools > Serial Plotter menu).
  Attach the center pin of a potentiometer to pin A0, and the outside pins to +5V and ground.

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/AnalogReadSerial
*/

int led = 13;
bool receiving = false;
unsigned char START_BYTE = 126;
unsigned char END_BYTE = 37;
unsigned char ESCAPE_BYTE = 63;
int serialIndex = 0;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  Serial.setTimeout(100);
}

// the loop routine runs over and over again forever:
void loop() {
  
  if (Serial.available()) {
    byte[] rawMsg = Serial.read();
    if (rawMsg[0] == START_BYTE) {
      receiving = true;
    }
  int i = 0;
  int dataLength = (int) rawMsg[1];
  byte[dataLength] escapedMsg;
  
  while(receiving) {
    if (rawMsg[0] = END_BYTE) {
      receiving = false;
      continue;
    }
    escapedMsg[i] = rawMsg[i];
  }

  int descapedDataLength = 0;
  
  for (int i = 0; i < dataLength; i++) {
    if (escapedMsg[i] == ESCAPE_BYTE) {
      continue;
    }
    descapedDataLength++;
  }

  byte descapedMsg[descapedDataLength];
  
  for (int i = 0; i < dataLength; i++) {
    if (escapedMsg[i] == ESCAPE_BYTE) {
      continue;
    }
    descapedMsg[i] = escapedMsg[i];
  }
  
  for (int i = 0; i < 100; i++) {
    Serial.println(descapedMsg[i]);
  }
}

void descape() {
  
}
