#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N,target;
    int max_answer = -1000000;
    int min_answer = 1000000;

    cin >> N;
    for(int i=0; i<N; i++) {
        cin >> target;
        max_answer = max(max_answer, target);
        min_answer = min(min_answer, target);
    }

    cout << min_answer << " " << max_answer << endl;
}