#include<iostream>
using namespace std;

void shift(int n, char a, char b){
	cout<<"\n Chuyen dia thu "<< n << " tu coc " << a << " sang coc " << b;
}

void towerHanoi(int n, char a, char b, char c){
	if (n==1)
		shift(1,a,c);
	else {
		towerHanoi(n-1,a,c,b);
		shift(n,a,c);
		towerHanoi(n-1,b,a,c);
	}
}

int main(){
	int n;
	char a = 'A', b = 'B', c = 'C';
	
	cout << "Nhap so dia N = ";
	cin>> n;
	
	towerHanoi(n,a,b,c);
	
	return 0;
}
