// Initial configuration file for app.js - nodejs and express server serving up hello world and a database check at /nodetest and /dbtest
// The IPs will have to be changed
// Also is dependent in this case on the nginx proxy pass (or whatever environment setup there is)

var express = require('express');
var app = express();
const mysql = require('mysql2'); //Adding for my sql
const port = 8000;
const dbport = 8001;

const mySQLinfo = {
        host: '***FILLINHOST****',
        user: 'lunchrush',
        password: '******',
        port: '3306'
};


const databasePool = mysql.createPool(mySQLinfo);


app.get('/dbtest', (req, res) => {
        databasePool.getConnection((err, connection) => {
                if (err) {
                        console.error('SQL is not working: ', err);
                        res.status(500).send('SQL is down');
                        return;
                }

                console.log('SQL connected');

                connection.release();

                res.send('SQL is active');
        });
});



app.listen(dbport, () => {
        console.log(`DB is listening on ${dbport}`);
});



app.listen(port, () => {
        console.log(`Server is up on ${port}`);
});





// Printing out hello world for test
app.get('/nodetest',(req, res) => {
        res.send('Hello World!');
});
