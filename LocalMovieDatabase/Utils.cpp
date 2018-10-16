#include "Utils.h"
#include <sstream>
#include <string>

using namespace std;

string trim(const string &str) {
	string result(str);
	int pos = result.find_first_not_of(" ");
	if (pos != -1)
		result.erase(0, pos);
	pos = result.find_last_not_of(" ");
	if (pos != string::npos)
		result.erase(pos + 1);

	return result;
}

void tokenize(string str, char delimiter, string tokens[], int &length) {
	length = 0;
	stringstream linestream(str);
	string item{};
	while (getline(linestream, item, delimiter)) {
		tokens[length++] = trim(item);
	}
}