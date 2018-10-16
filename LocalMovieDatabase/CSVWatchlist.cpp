#include "CSVWatchlist.h"
#include <iostream>
#include <fstream>
#include <Windows.h>
//#include "RepositoryExceptions.h"

using namespace std;

void CSVWatchlist::writeToFile()
{
	ofstream f(this->filename);

	if (!f.is_open())
		std::cout << "The file could not be opened!";
	else {
		for (auto s : this->Watchlist_Movies)
			f << s;
	}
	f.close();
}

void CSVWatchlist::displayWatchlist() const
{
	string aux = "\"" + this->filename + "\""; // if the path contains spaces, we must put it inside quotations
											   //ShellExecuteA(NULL, NULL, "C:\\Program Files (x86)\\OpenOffice 4\\program\\scalc.exe", aux.c_str(), NULL, SW_SHOWMAXIMIZED);
	ShellExecuteA(NULL, NULL, "C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\EXCEL.EXE", aux.c_str(), NULL, SW_SHOWMAXIMIZED);
}
