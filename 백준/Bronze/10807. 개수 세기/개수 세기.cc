#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n,v,answer;
    answer = 0;
    cin >> n;

    vector<int> arr(n);
    for(int i=0; i<n; i++) {
        cin >> arr[i]; 
    }

    cin >> v;
    for(int i=0; i<n; i++) {
        if(arr[i] == v) {
            answer+=1;
        }
    }

    cout << answer << endl;
}