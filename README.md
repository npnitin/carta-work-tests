# carta-work-tests

This is the test assignment created for test purpose.

Basically in this assignment, i have created an API endpoint which will accept CSV file and column name as input and return averge value for that column.

Endpoint looks like :http://localhost:8080?column=coulmn_name with csv file as post data.

Below is the sample request:

Sample input file like :

name,number
A,41
B,42
C,43

Sample output would be like for coulm number:
{
    "data": 42
}
