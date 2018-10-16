#pragma once
#include "FileWatchlist.h"
#include <string>

class HTMLWatchlist : public FileWatchlist
{
public:
	/*
	Writes the watchlist to file.
	Throws: FileException - if it cannot write.
	*/
	void writeToFile() override;

	/*
	Displays the watchlist using Google Chrome.
	*/
	void displayWatchlist() const override;
};

