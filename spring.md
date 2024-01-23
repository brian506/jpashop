# 스프링 기본 개념 정리

## 엔티티와 테이블 매핑
- 객체와 테이블 매핑: @Entity,@Table
- 기본 키 매핑 : @id
- 자동 생성 : @GeneratedValue
- 필드와 컬럼 매핑 : @Column
- 연관관계 매핑 : @ManyToOne, @joinColumn

### @Entity
-> 테이블과 매핑할 클래스는 @Entity 붙여야 함
-> 보통 기본값인 클래스 이름을 사용
※주의 사항
1. 기본 생성자는 필수(public,protected)
2. final,enum,interface,inner 클래스 사용 불가
3. 저장할 필드에 final 붙이면 안됨
### @Table
-> 엔티티와 매핑할 테이블 지정
-> 생략하면 매핑한 테이블 이름을 테이블 이름으로 사용

### @Embedded
-> 상세 데이터(주소,번지,등)을 하나의 객체로 묶어 표현
@Embeddable : 값 타입을 정의하는 곳에 표시
@Embedded : 값 타입을 사용하는 곳에 표시
※ 기본 생성자 필수

### @JoinColumn
-> 외래 키를 매핑할 때 사용
-> 주인과 하인 관계일 때 주인 클래스에 하인 클래스를 매핑할때 사용
 ex) order클래스에 member테이블 매핑 -> @JoinColumn(name = "member_id)

### @Enumerated
-> 자바의 enum타입을 매핑할 때 사용
EnumType.STRING을 주로 사용-> 문자 그대로 db에 저장

## 상속관계 매핑
객체는 상속관계가 존재하지만 db는 상속관계가 없다
### @Inheritance(strategy=InheritanceType.XXX)의 stategy를 설정해주면 된다.
default 전략은 SINGLE_TABLE(단일 테이블 전략)이다.
InheritanceType 종류
- JOINED
- SINGLE_TABLE
- TABLE_PER_CLASS
### @DiscriminatorColumn(name="DTYPE")
-> 부모 클래스에 선언한다. 
-> 하위 클래스를 구분하는 용도의 컬럼이다.
-> 관례는 default = DTYPE
### @DiscriminatorValue("XXX")
-> 하위 클래스에 선언한다. 
-> 엔티티를 저장할 때 슈퍼타입의 구분 컬럼에 저장할 값을 지정한다.
-> 어노테이션을 선언하지 않을 경우 기본값으로 클래스 이름이 들어간다




