#pragma once
#include "Movie.h"
#include <string>


class ValidationException
{
private:
	std::string message;

public:
	ValidationException(std::string _message);
	std::string getMessage() const;
};

class MovieValidator
{
public:
	/*
	Valides all the fields of a given movie.
	Input: the movie to be validated.
	Output: throws an ValidationException if at least one field of the movie is not valid.
	*/
	static void validate(const Movie& m);
};

