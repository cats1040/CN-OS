// Google Interview
#include <bits/stdc++.h>

using namespace std;

class Message
{
public:
    string message;
    int timestamp;

    Message()
    {
    }

    Message(int timestamp, string message)
    {
        this->timestamp = timestamp;
        this->message = message;
    }
};

class Logger
{
private:
    unordered_map<string, int> lastKnownTimestamp;

public:
    Logger()
    {
    }

    Message getLatestMessage()
    {
        return Message(0, "");
    }

    void pollMessage()
    {
        while (true)
        {
            Message m;
            shouldPrintMessagev2(m.timestamp, m.message);
        }
    }

    bool shouldPrintMessage(int timestamp, string message)
    {
        if (lastKnownTimestamp.count(message))
        {
            if (timestamp - lastKnownTimestamp[message] >= 10)
            {
                lastKnownTimestamp[message] = timestamp;
                cout << "True\n";
                return true;
            }
            else
            {
                cout << "False\n";
                return false;
            }
        }
        lastKnownTimestamp[message] = timestamp;
        cout << "True\n";
        return true;
    }

    bool shouldPrintMessagev2(int timestamp, string message)
    {
        /**
         * any message that have been received
         * in 10s should be ignored
         * input:
         * 1 hello 2 bye 4 hello 1 bye 15 bye
         * output:
         * 2 bye
         */
    }
};

int main()
{
    Logger logger = Logger();
    logger.shouldPrintMessage(1, "foo");  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
    logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
    logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
    logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
    logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
    logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21

    return 0;
}