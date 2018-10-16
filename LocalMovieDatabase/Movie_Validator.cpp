#include "Movie_Validator.h"

ValidationException::ValidationException(std::string _message) : message{ _message } {}

std::string ValidationException::getMessage() const
{
	return this->message;
}

void MovieValidator::validate(const Movie & m)
{
	std::string errors;


	if (m.get_title().size() <= 0)
		errors += std::string("The movie title cannot be empty!\n");
	if (m.get_genre().size() <= 0)
		errors += std::string("The movies genre cannot be empty!\n");
	if (m.get_year() < 1950)
		errors += std::string("Your movie cannot be older than 1950!\n");
	if (m.get_nr_likes() <= 0)
		errors += std::string("Number of likes must be a positive number!\n");
	// search for "www" or "http" at the beginning of the source string
	int posWww = m.get_url().find("www");
	int posHttp = m.get_url().find("http");
	if (posWww != 0 && posHttp != 0)
		errors += std::string("The youtube source must start with one of the following strings: \"www\" or \"http\"");

	if (errors.size() > 0)
		throw ValidationException(errors);


}

