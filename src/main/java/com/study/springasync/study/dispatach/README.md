Toby님의 유튜브 강의를 보며 공부한 자료(그대로 따라한 것임) <br>
[>link](https://www.youtube.com/channel/UCcqH2RV1-9ebRBhmN_uaSNg)

# dispatch
어떤 메소드를 호출할지 결정하는 과정
> Dispatch is the way a language links calls to function/method definitions.
> * ref (https://stackoverflow.com/questions/5508274/what-is-dispatching-in-java)

# Static dispatch
(프로그래밍 실행 시점이 되지 않아도)컴파일 시점에 어느 메소드가 불릴지 알고 있는 것
> static dispatch is a form of polymorphism fully resolved during compile time. 
> * ref (https://en.wikipedia.org/wiki/Static_dispatch)

# Dynamic dispatch
런타임 시점에 어느 메소드를 호출할지 동적으로 정하는 것. 
> dynamic dispatch is the process of selecting which implementation of a polymorphic operation (method or function) to call at run time. 
> * https://en.wikipedia.org/wiki/Dynamic_dispatch

# Double dispatch(Dynamic dispatch)
Dynamic dispatch가 두 번 일어난 것.
디자인패턴에서는 visitor pattern이라고 정의. 

# etc.
### Method Signature (overriding 할때) 
* method name
* parameter types (파라미터 타입, 갯수)<br>
:return type는 메소드 시그니쳐가 아님을 주의

### Method Type (= Method Reference. java8)
* return type
* method type parameter
* method argument types
* exception
