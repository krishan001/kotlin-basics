# Coursework 2 Datasets

This coursework involves the analysis of meteorological data collected
over a number of years [from weather stations around the UK][1].  The files
`bradford.txt`, `durham.txt`, `lerwick.txt`, `oxford.txt` and `sheffield.txt`
are cleaned-up versions of five of these datasets.  Your application should
be able to read and analyse each of these datasets successfully.

Each file has the same format, consisting of a four-line **header** and then
a series of **records**, each containing data for a single month.  The fields
in a record are year, month, mean daily maximum temperature (degrees Celsius),
mean daily minimum temperature (degrees Celsius), days of air frost,
rainfall (mm) and number of hours of sunshine.  **Only the year, month and
rainfall values are relevant for this coursework.**  You should ignore all of
the other fields.

Five other files are provided to help you test your application:

* `bad-file.txt` contains only a single line.

* `bad-year.txt` contains a valid header plus a single record in which
  the year is invalid, where invalid is defined as 'earlier than 1930'.

* `bad-month.txt` contains a valid header plus a single record in
  which the month is invalid - i.e., outside the range 1-12.

* `bad-rainfall.txt` contains a valid header plus a single record in
  which the rainfall value is invalid - i.e., negative.

Your application should terminate with an appropriate and informative error
when it attempts to read each of these files.

The remaining file `header-only.txt` contains the 4 required header lines
but no data.  Reading this should *not* result in an error.  Instead, your
application should display the details extracted from the header, indicate
that the number of records is zero and then terminate.

[1]: http://www.metoffice.gov.uk/public/weather/climate-historic/
