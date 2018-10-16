#include "FileWatchlist.h"

FileWatchlist::FileWatchlist() : Watchlist{}, filename{ "" }
{
}

void FileWatchlist::setFilename(const std::string& filename)
{
	this->filename = filename;
}