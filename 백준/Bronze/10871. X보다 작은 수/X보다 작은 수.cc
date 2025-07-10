#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n,x,target;
    cin >> n >> x;

    for(int i=0; i<n; i++) {
        cin >> target;
        if(target < x) {
            cout << target << " ";
        }
    }

    cout << endl;
}