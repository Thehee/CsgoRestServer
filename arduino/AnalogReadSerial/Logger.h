#ifndef Logger_h
#define Logger_h

class Logger {
  public:
    Logger(bool write);
    void log(byte msg);
    void logString(String msg);
    void logInt(int msg);
    void enable();
    void disable();
    void newLine();

  private:
    bool _write;
};

#endif
