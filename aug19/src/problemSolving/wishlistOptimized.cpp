// Copyright 2025 Shreya Sharma

// Asked in Google!

#include <bits/stdc++.h>

using namespace std;

class Node {
public:
    int freq;
    Node *next;
    Node *prev;

    set<string> products;

    Node(int freq) {
        this.freq = freq;
    }
};

class Wishlist {
private:
    unordered_map<string, int> productFreq;
    unordered_map<string, Node *> productNodeMap;
    Node head, tail;

    void insertAfter(Node *curr, Node *next) {
        Node *currNext = curr->next;
        next->next = currNext;
        currNext->prev = next;
        curr->next = next;
        next->prev = curr;
    }

public:
    Wishlist() {
        head = new Node(-1);
        tail = new Node(-1);

        head.next = tail;
        tail.prev = head;
    }

    void inc(string key) {
        
    }

    void dec(string key) {

    }
    
    string getMin() {

    }

    string getMax() {

    }
};

int main() {

    return 0;
}
