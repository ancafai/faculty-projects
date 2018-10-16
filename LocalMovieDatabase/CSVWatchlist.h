#pragma once
#include "FileWatchlist.h"
#include <string>

class CSVWatchlist : public FileWatchlist
{
public:
	/*
	Writes the playlist to file.
	Throws: FileException - if it cannot write.
	*/
	void writeToFile() override;

	/*
	Displays the playlist using Microsof Excel.
	*/
	void displayWatchlist() const override;
};

