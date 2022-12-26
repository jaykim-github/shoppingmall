# shoppingmall
<br>
:page_with_curl: 프로젝트 정보
<br>

- 미니 쇼핑몰 구현 프로젝트
- 대용량 트래픽을 가정, 많은 트래픽을 발생하는 지점에 Redis를 이용 Cache 데이터를 사용하려 했었다.
- Write Back 패턴을 사용하여 구현하려 했으나, 구현하면서 오류로 인해 시간상 Redis cache를 사용하는 것에서 그쳤다.
- 결제 구현하면서, 어떤 방식을 사용해볼까 하다 스스로가 많이 쓰고있는 카카오페이 결제 방식을 구현하기로 결정했다.
- 카카오페이 Api는 사업자 번호 없이 테스트용으로 코드를 따로 제공해주고 있어서 편리하게 결제를 구현할 수 있었다.

<br>

:hammer: 개발 스택
- Java, Gradle, Spring boot
- Maria DB, JPA, Redis
- Spring security, jwt 
- kakaoPay Api

<br>

:black_nib: 기능 설명

<br>

:one: Banner : 관리자 권한이 있는 User만, 등록, 조회, 상세보기, 수정, 삭제가 가능 

<br>

:two: CouponMaster : 관리자 권한이 있는 User만 쿠폰 마스터 등록, 조회, 수정, 삭제가 가능

<br>

:three: Coupon : 로그인 한 User당 한 번, 쿠폰이 소진 되지 않은 경우 User가 쿠폰 발급이 가능

<br>

:four: Cart : 로그인 한 User는 결제를 위해 카트에 상품을 담을 수 있고, 상품 삭제가 가능

<br>

:five: User : User는 회원 가입 후 이메일을 인증해야 로그인 가능, 회원 가입과 이메일 인증, 로그인은 권한이 없어도 접근이 가능

<br>

:six: Payment : <br>
로그인 한 User는 카트에 상품이 있으면 결제 요청을 할 수 있고, 결제는 카카오페이 Api를 통해 결제 요청 후 승인되는 구조. <br>
결제 시 사용했던 결제 ID 값으로 결제된 건들에 한에 삭제가 가능. 부분 결제는 불가능 하고, 결제한 건에 대해 가능

<br>


:file_folder: 프로젝트 구조

<br>

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂zerobase
 ┃ ┃ ┃ ┗ 📂shoppingmall
 ┃ ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┃ ┃ ┣ 📂banner
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BannerController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Banner.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BannerInput.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BannerStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BannerRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂Impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BannerServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BannerService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂coupon
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CouponMasterController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Coupon.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CouponMaster.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponInput.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CouponMasterInput.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponMasterRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CouponRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂Impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponMasterServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CouponServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponMasterService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CouponService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂product
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProductController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Product.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductInput.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProductStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProductRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProductServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProductService.java
 ┃ ┃ ┃ ┃ ┣ 📂cart
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CartController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Cart.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CartInput.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CartRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CartServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CartService.java
 ┃ ┃ ┃ ┃ ┣ 📂component
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MailComponents.java
 ┃ ┃ ┃ ┃ ┣ 📂configuration
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AppConfig.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜CacheConfig.java
 ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┣ 📂Impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadtyAuthedEmailException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadtyExistUserException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadtyInCartException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadtyIssuedCouponException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NoAuthedEmailException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NoBannerException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NoCouponMasterException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NoLeftCountCouponException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NoProductException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NoProductInCartException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotCartException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotExistUserException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotMatchPasswordException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotPaymentHistoryException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WrongAuthEmailException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ErrorResponse.java
 ┃ ┃ ┃ ┃ ┣ 📂payment
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaymentController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Payment.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PaymentInput.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaymentStatus.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaymentRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂Impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaymentServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaymentService.java
 ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BaseResponse.java
 ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationFilter.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜SecurityConfiguration.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenProvider.java
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Authority.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserInput.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┃ ┃ ┃ ┗ 📜ShoppingmallApplication.java
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜application.yml
 ┣ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂zerobase
 ┃ ┃ ┃ ┗ 📂shoppingmall
 ┃ ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┃ ┃ ┗ 📂coupon
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂Impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CouponServiceImplTest.java
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserServiceImplTest.java
 ┃ ┃ ┃ ┃ ┗ 📜ShoppingmallApplicationTests.java
 ┗ 📜intellij-java-google-style.xml
 ```

<br>
