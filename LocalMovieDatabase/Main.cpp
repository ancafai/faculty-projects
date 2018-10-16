#include "UI_Administrator.h"
#include "UI_User.h"
#include "CSVWatchlist.h"
#include "HTMLWatchlist.h"
#include <Windows.h>
#include <crtdbg.h>
#include <iostream>
using namespace std;

void show_main_menu() {
	cout << endl;
	cout << "1 - Administrator login." << endl;
	cout << "2 - User login." << endl;
	cout << "0 - Exit." << endl;
}

int main() {

	system("color f4");

	Admin_Repo repo_admin("Movies.txt");

	

	while (true) {
		Admin_Ctrl ctrl_admin(repo_admin);
		Admin_UI ui_admin(ctrl_admin);
		string answer;
		while (true) {
			cout << "CSV/HTML?\n";
			cin >> answer;
			if (answer == "CSV") {
				FileWatchlist* file = new CSVWatchlist{};
				User_Ctrl user_ctrl(repo_admin, file);
				User_UI ui_user(user_ctrl);
				show_main_menu();
				int command{ 0 };
				cout << "Input the command: ";
				cin >> command;
				cin.ignore();
				if (command == 0) {
					break;
				}

				//Administrator
				if (command == 1) {
					ui_admin.run_admin();
				}
				else if (command == 2) {
					ui_user.run_user();
				}
				delete file;
				break;
			}
			else if (answer == "HTML") {
				FileWatchlist* file = new HTMLWatchlist{};
				User_Ctrl user_ctrl(repo_admin, file);
				User_UI ui_user(user_ctrl);
				show_main_menu();
				int command{ 0 };
				cout << "Input the command: ";
				cin >> command;
				cin.ignore();
				if (command == 0) {
					break;
				}

				//Administrator
				if (command == 1) {
					ui_admin.run_admin();
				}
				else if (command == 2) {
					ui_user.run_user();
				}
				delete file;
				break;
			}
			else {
				cout << "Please input an option\n";
			}
		}
	}
	
	_CrtDumpMemoryLeaks();
	return 0;
}