Toby님의 유튜브 강의를 보며 공부한 자료(그대로 따라한 것임) <br>
[>link](https://www.youtube.com/channel/UCcqH2RV1-9ebRBhmN_uaSNg)

# Async vs Sync
* Async
> ddd

* Sync
> ddd

# Non-Blocking I/O vs Blocking I/O
* Non-Blocking I/O (NIO)
> ddd

* Blocking I/O
> ddd


# Thread
### ServletThread
> 웹 요청을 처리하기 위해 할당하는 스레드

### WorkerThread
> 작업을 처리하기 위한 스레드

### NIO,Async Cycle (Worker Thread, Servlet Thread)
> 비동기 서블릿을 이용한 스프링의 비동기 서블릿 처리
<img src="img/AsyncCycle.png" width=100% height="400">

NIO connector가 클라이언트에서부터 요청을 받는다.
그러면 서블릿 스레드풀에서부터 서블릿 스레드을 발행해서 처리한다.

그 중 비동기 작업(@async)이 할 것이 있으면
작업스레드에서 진행중인 작업을 기다리는 것이 아니라

그대로 서블릿 스레드를 풀에 반환다.

이때 문제가 되는 것은 http라는 것은 요청이 갔으면 응답이 와야하는 것인데 (json resopone status)
응답을 처리하는건 누가될것인가

이거는 비동기 서블릿 엔진 자체가 작업스레드의 작업 완료 후에 다시 스레드를 발행해서
아주 빠르게 nio connector에 반환한다.(커넥터는 물고 있는 상태임으로 물고있는 커넥터에 반환한다)
그리고 스레드를 반환한다.

적은 스레드풀을 운영해서 꽤 많은 작업을 동시에 처리할 수 있다.

실은 서블릿 스레드풀이 적게 운영 되는 만큼 뒤에 워커 스레드가 그 부담이 커지는 것이다.
이는 빠르게 http요청을 반환하고 뒤에 무거운 작업을 여러개 동시에 하는 일에 적합하다.

# DefferedResult 큐
> 워커스레드를 생성하지 않고 처리하는 방법
// TODO 그림 2:03:15

지연된 결과. 외부의 이벤트에 의해서 또는 클라이언트의 요청에 의해서 기존에 지연된 http응답을 나중에 하는 기술이 디퍼드리저트.











 