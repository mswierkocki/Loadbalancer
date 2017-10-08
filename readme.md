# LoadBalancer
Simple REST application that route traffic to different backends depending on a group that user falls into.

#### Usage
Application binds to ``localhost`` on port ``8888``

To run use:
> 
>	 ./gradlew run
>

 
Configuration is stored in file **main/resources/config.properties** 

*each group must define name and weight that sums up to 100  

```
group[n].name = name of group
group[n].weigth = weight of group
```
where n is corresponding group number from 0..N-1 where N is number of all groups

E.g.:

  ```
hash_method=Extern
group[0].name=A
group[0].weigth=30
group[1].name=B
group[1].weigth=20
group[2].name=C
group[2].weigth=50

  ```
* hash_method can be set with 3 options:
--Extern for CRC32 hashing method;
--Own for CustomHash method witch can be found in utis package
--None for using default String.toHash
  
*  Example API input:
  ``` curl http://localhost:8888/route?id={id} ```

#### Benchmark

Testing platform: Intel i5-2550K 3.4GHz x 4


 `ab -n1000000 -c10 http://localhost:8888/route?id= `
 
 -* Results for Extern CRC32:
   ```
 Server Software:        
Server Hostname:        localhost
Server Port:            8888

Document Path:          /route?id=
Document Length:        1 bytes

Concurrency Level:      10
Time taken for tests:   70.898 seconds
Complete requests:      1000000
Failed requests:        0
Total transferred:      135000000 bytes
HTML transferred:       1000000 bytes
Requests per second:    14104.74 [#/sec] (mean)
Time per request:       0.709 [ms] (mean)
Time per request:       0.071 [ms] (mean, across all concurrent requests)
Transfer rate:          1859.51 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.1      0       9
Processing:     0    1   0.6      1     146
Waiting:        0    1   0.6      1     146
Total:          0    1   0.6      1     146

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      1
  75%      1
  80%      1
  90%      1
  95%      1
  98%      2
  99%      2
 100%    146 (longest request)
  ```
-* Results for Own hash algorithm:
  ```
Server Software:        
Server Hostname:        localhost
Server Port:            8888

Document Path:          /route?id=
Document Length:        1 bytes

Concurrency Level:      10
Time taken for tests:   68.959 seconds
Complete requests:      1000000
Failed requests:        0
Total transferred:      135000000 bytes
HTML transferred:       1000000 bytes
Requests per second:    14501.27 [#/sec] (mean)
Time per request:       0.690 [ms] (mean)
Time per request:       0.069 [ms] (mean, across all concurrent requests)
Transfer rate:          1911.79 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.1      0      11
Processing:     0    1   0.6      1     146
Waiting:        0    1   0.6      1     146
Total:          0    1   0.6      1     146

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      1
  75%      1
  80%      1
  90%      1
  95%      1
  98%      2
  99%      2
 100%    146 (longest request)
   ```
-* Results for String.hashCode algorithm:
  ```
Server Software:        
Server Hostname:        localhost
Server Port:            8888

Document Path:          /route?id=
Document Length:        1 bytes

Concurrency Level:      10
Time taken for tests:   69.402 seconds
Complete requests:      1000000
Failed requests:        0
Total transferred:      135000000 bytes
HTML transferred:       1000000 bytes
Requests per second:    14408.80 [#/sec] (mean)
Time per request:       0.694 [ms] (mean)
Time per request:       0.069 [ms] (mean, across all concurrent requests)
Transfer rate:          1899.60 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.1      0      15
Processing:     0    1   0.6      1     137
Waiting:        0    1   0.6      1     136
Total:          0    1   0.6      1     137

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      1
  75%      1
  80%      1
  90%      1
  95%      1
  98%      2
  99%      3
 100%    137 (longest request)

  ```
