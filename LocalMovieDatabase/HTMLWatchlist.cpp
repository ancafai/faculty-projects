#include "HTMLWatchlist.h"
#include <fstream>
#include <Windows.h>
#include <iostream>
#include "RepositoryExceptions.h"

using namespace std;

void HTMLWatchlist::writeToFile()
{
	ofstream f(filename);
	if (!f.is_open())
		std::cout << "The file could not be opened!";
	else {
		f << "<!DOCTYPE html>\n";
		f << "<html>" << "\n";
		f << "<head>" << "\n";
		f << "<title>Watchlist</title>" << "\n";
		f << "<link rel='stylesheet' type='text/css' href='css/style.css'>" << "\n";
		f << "</head>" << "\n";
		f << "<body>" << "\n";
		f << "<table>" << "\n";
		f << "<thead>" << "\n";
		f << "<tr>" << "\n";
		f << "<th>Title</td>" << "\n";
		f << "<th>Genre</td>" << "\n";
		f << "<th>Year</td>" << "\n";
		f << "<th>Likes</td>" << "\n";
		f << "<th>Youtube link</td>" << "\n";
		f << "</tr>" << "\n";
		f << "</thead>" << "\n";
		for (auto i : this->get_movies())
		{
			f << "<tr>";
			f << "<td>" << i.get_title() << "</td>" << "\n";
			f << "<td>" << i.get_genre() << "</td>" << "\n";
			f << "<td>" << i.get_year() << "</td>" << "\n";
			f << "<td>" << i.get_nr_likes() << "</td>" << "\n";
			f << "<td><a href =" << i.get_url() << "> Trailer</a></td>" << "\n";
			f << "</tr>";
		}
		f << "</table>" << "\n";
		f << "</body>" << "\n";
		f << "</html>" << "\n";
	}
		f.close();
}

void HTMLWatchlist::displayWatchlist() const
{
	string aux = "\"" + this->filename + "\""; // if the path contains spaces, we must put it inside quotations
											   //ShellExecuteA(NULL, NULL, "C:\\Program Files (x86)\\OpenOffice 4\\program\\scalc.exe", aux.c_str(), NULL, SW_SHOWMAXIMIZED);
	ShellExecuteA(NULL, NULL, "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", aux.c_str(), NULL, SW_SHOWMAXIMIZED);
}
