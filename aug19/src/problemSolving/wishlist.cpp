// Copyright 2025 Shreya Sharma

// Asked in Google!

#include <bits/stdc++.h>

using namespace std;

class Wishlist {
private:
    unordered_map<string, int> count;
    
    struct cmp {
        unordered_map<string,int> *cnt;
        cmp(unordered_map<string,int> *c) : cnt(c) {}

        bool operator()(const string &a, const string &b) const {
            if ((*cnt)[a] == (*cnt)[b])
                return a < b; 
            return (*cnt)[a] > (*cnt)[b];
        }
    };

    set<string, cmp> product;

public:
    Wishlist() {
        cmp comparator(&count);
        product = set<string, cmp>(comparator);   
    }

    // O(log(n))
    void inc(string key) {
        count[key]++;
        product.erase(key);
        product.insert(key);
    }

    // O(log(n))
    void dec(string key) {
        count[key]--;
        product.erase(key);

        if (count[key] == 0) {
            count.erase(key);
            return;
        }

        product.insert(key);
    }

    // O(1)
    string getMin() {
        return *product.rbegin();
    }
    
    // O(1)
    string getMax() {
        return *product.begin();
    }
};

int main() {
    return 0;
}
