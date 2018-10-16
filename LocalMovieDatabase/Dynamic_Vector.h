#pragma once
#include "Movie.h"
#include <assert.h>
typedef Movie T;
template<class T>
class Dynamic_Vector {
private:
	T *arr;
	int size;
	int cap;
public:

	//default constructor for a Dynamic_Vector
	Dynamic_Vector();

	//copy constructor for a Dynamic_Vector
	Dynamic_Vector(const Dynamic_Vector &vector);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Destructor. </summary>
	///
	/// <remarks>	Faiciuc Anca, 07.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	~Dynamic_Vector();	//Destroy

	// assignement operator for a Dynamic_Vector
	Dynamic_Vector<T> &operator=(Dynamic_Vector<T> const &vector);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Array indexer operator. </summary>
	///
	/// <remarks>	Faiciuc Anca, 07.04.2016. </remarks>
	///
	/// <param name="pos">	The position. </param>
	///
	/// <returns>	The indexed value. </returns>
	///-------------------------------------------------------------------------------------------------

	T& operator[](int pos) const;

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Addition operator. </summary>
	///
	/// <remarks>	Cristian Cosmin, 07.04.2016. </remarks>
	///
	/// <param name="elem">	The element. </param>
	///
	/// <returns>	The result of the operation. </returns>
	///-------------------------------------------------------------------------------------------------

	Dynamic_Vector<T> &operator+(const T &elem);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Subtraction operator. </summary>
	///
	/// <remarks>	Faiciuc Anca, 07.04.2016. </remarks>
	///
	/// <param name="elem">	The element. </param>
	///
	/// <returns>	The result of the operation. </returns>
	///-------------------------------------------------------------------------------------------------

	Dynamic_Vector<T> &operator-(const T &elem);

	/*// Adds an element to the current Dynamic_Vector
	void add_vector(T elem);

	// Deletes an element from the current Dynamic_Vector
	void delete_vector(int pos);*/

	//Updates an element from the current Dynamic_Vector
	void update_vector(const T &elem, int pos);

	//Returns the array containing all the elements from the Dynamic_Vector
	T* get_all_elems() const { return arr; };

	// Resizes the current Vector, multiplying its capacity by a given factor (real number)
	void resize(int factor = 2);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets the capacity. </summary>
	///
	/// <remarks>	Faiciuc Anca, 07.04.2016. </remarks>
	///
	/// <returns>	The capacity. </returns>
	///-------------------------------------------------------------------------------------------------

	int get_capacity() const { return this->cap; }

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets the size. </summary>
	///
	/// <remarks>	Faiciuc Anca, 07.04.2016. </remarks>
	///
	/// <returns>	The size. </returns>
	///-------------------------------------------------------------------------------------------------

	int get_size() const { return this->size; }

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Increment size. </summary>
	///
	/// <remarks>	Faiciuc Anca, 07.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void inc_size() { size++; }
		
};

Dynamic_Vector<T> &operator+(const T &elem, Dynamic_Vector<T> &vector);

template<typename T>
Dynamic_Vector<T>::Dynamic_Vector() {
	this->size = 0;
	this->cap = 10;
	this->arr = new T[cap];
}
template<typename T>
Dynamic_Vector<T>::Dynamic_Vector(const Dynamic_Vector &vector) {
	this->size = vector.size;
	this->cap = vector.cap;
	this->arr = new T[this->cap];
	for (int i = 0; i < this->size; i++)
		this->arr[i] = vector.arr[i];
}
template<typename T>
Dynamic_Vector<T>::~Dynamic_Vector() {
	delete[] this->arr;
}
template<typename T>
Dynamic_Vector<T> &Dynamic_Vector<T>::operator=(const Dynamic_Vector &vector) {
	if (this == &vector)
		return *this;
	this->size = vector.size;
	this->cap = vector.cap;

	delete[] this->arr;
	this->arr = new T[this->cap];
	for (int i = 0; i < this->size; i++)
		this->arr[i] = vector.arr[i];

	return *this;
}

template<typename T>
Dynamic_Vector<T> &Dynamic_Vector<T>::operator+(const T &elem) {
	if (this->size == this->cap)
		this->resize();
	this->arr[this->size] = elem;
	this->size++;

	return *this;
}
template<typename T>
Dynamic_Vector<T> &Dynamic_Vector<T>::operator-(const T &elem) {
	for (int i = 0; i < this->size; i++)
	{
		if (this->arr[i] == elem)
		{
			for (int j = i + 1; j < this->size; j++) {
				this->arr[j - i] = this->arr[j];
			}
		}
	}

	this->size--;
	return *this;
}
template<typename T>
void Dynamic_Vector<T>::update_vector(const T &elem, int pos) {
	this->arr[pos] = elem;
}
template<typename T>
T& Dynamic_Vector<T>::operator[](int pos) const
{
	return this->arr[pos];
}

template<typename T>
void Dynamic_Vector<T>::resize(int factor) {
	this->cap *= factor;

	T* els = new T[this->cap];
	for (int i = 0; i < this->size; i++)
		this->arr[i] = els[i];

	delete[] this->arr;
	arr = els;
}

template<typename T>
Dynamic_Vector<T> &operator+(const T &elem, Dynamic_Vector<T> &vector) {
	if (vector.get_capacity() > vector.get_size())
		vector.resize();
	vector.get_all_elems()[vector.get_size()] = elem;
	vector.inc_size();

	return vector;
}



