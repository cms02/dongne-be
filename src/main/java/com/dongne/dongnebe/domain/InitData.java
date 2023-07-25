package com.dongne.dongnebe.domain;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.domain.board.repository.BoardRepository;
import com.dongne.dongnebe.domain.category.channel.entity.Channel;
import com.dongne.dongnebe.domain.category.channel.repository.ChannelRepository;
import com.dongne.dongnebe.domain.category.main_category.entity.MainCategory;
import com.dongne.dongnebe.domain.category.main_category.enums.MainCategoryType;
import com.dongne.dongnebe.domain.category.main_category.repository.MainCategoryRepository;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.category.sub_category.repository.SubCategoryRepository;
import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.city.repository.CityRepository;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.board_comment.repository.BoardCommentRepository;
import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.domain.comment.reply_comment.repository.ReplyCommentRepository;
import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_comment_likes.repository.BoardCommentLikesRepository;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import com.dongne.dongnebe.domain.likes.board_likes.repository.BoardLikesRepository;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.entity.ReplyCommentLikes;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.repository.ReplyCommentLikesRepository;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.domain.zone.repository.ZoneRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

@Profile({"local","dev","prod"})
@Component
@RequiredArgsConstructor
public class InitData {

    private final InitCityService initCityService;
    private final InitZoneService initZoneService;
    private final InitUserService initUserService;
    private final InitMainCategoryService initMainCategoryService;
    private final InitSubCategoryService initSubCategoryService;
    private final InitBoardService initBoardService;
    private final InitChannelService initChannelService;
    private final InitBoardCommentService initBoardCommentService;
    private final InitReplyCommentService initReplyCommentService;
    private final InitBoardLikesService initBoardLikesService;
    private final InitBoardCommentLikesService initBoardCommentLikesService;
    private final InitReplyCommentLikesService initReplyCommentLikesService;

    private static final String[] NAMES = {
            "김가영", "이나은", "박다은", "최라윤", "정마린", "강바다", "윤사라", "한아름", "서자은", "황찬미",
            "송한결", "조강민", "임남우", "신단비", "유란제", "임매리", "조백호", "서서진", "윤안나", "박유진",
            "한지영", "류철수", "홍희진", "김가인", "이나은", "박다연", "최라온", "정마을", "강바우", "윤사나",
            "한아현", "서자하", "황찬미", "송한별", "조강우", "임남준", "신단우", "유란울", "임매현", "조백하",
            "서서영", "윤안나", "박유진", "한지훈", "류철우", "홍희철", "김가람", "이나라", "박다은", "최라은",
            "정마린", "강바다", "윤사라", "한아름", "서자은", "황찬미", "송한결", "조강민", "임남우", "신단비",
            "유란제", "임매리", "조백호", "서서진", "윤안나", "박유진", "한지영", "류철수", "홍희진", "김가인",
            "이나은", "박다연", "최라온", "정마을", "강바우", "윤사나", "한아현", "서자하", "황찬미", "송한별",
            "조강우", "임남준", "신단우", "유란울", "임매현", "조백하", "서서영", "윤안나", "박유진", "한지훈",
            "류철우", "홍희철", "김가람", "이나라", "박다은", "최라은", "추민석", "방주현", "최재성", "김덕환"
    };

    private static final String[] BOARD_TITLE = {
            "10가지 실전 자바 팁",
            "웹 개발에서 가장 중요한 보안 요소",
            "초보자를 위한 프로그래밍 기초 강좌",
            "데이터베이스 설계 원칙과 팁",
            "효율적인 알고리즘을 위한 팁",
            "최신 프론트엔드 프레임워크 비교",
            "클라우드 컴퓨팅의 장점과 활용 방법",
            "머신러닝을 활용한 예측 분석 기법",
            "안드로이드 앱 개발의 핵심 기술",
            "블록체인 기술의 혁신과 응용 분야",
            "소프트웨어 개발 프로세스와 방법론",
            "Git을 활용한 협업 개발 가이드",
            "컴퓨터 네트워킹 기초 이해하기",
            "안전한 웹 애플리케이션 개발 방법",
            "데이터 분석을 위한 파이썬 기초",
            "모바일 앱 테스팅의 주요 전략",
            "사용자 경험 디자인의 핵심 원칙",
            "자바스크립트 비동기 프로그래밍",
            "데이터 구조와 알고리즘의 이해",
            "웹 보안을 위한 OWASP Top 10",
            "데이터베이스 성능 최적화 기법",
            "테스트 주도 개발(TDD)의 원리",
            "클린 코드 작성을 위한 팁",
            "마이크로서비스 아키텍처 설계",
            "사이버 보안 위협 대응 전략",
            "객체 지향 프로그래밍의 원리",
            "리액트 네이티브를 활용한 모바일 앱 개발",
            "클라우드 기반 서버리스 아키텍처",
            "데이터 시각화를 위한 도구와 기법",
            "사용자 중심의 웹 디자인 가이드",
            "빅데이터 처리를 위한 Apache Spark",
            "실시간 데이터 처리를 위한 Apache Kafka",
            "네트워크 보안을 위한 최신 기술 동향",
            "애자일 소프트웨어 개발 방법론",
            "모바일 앱 보안을 위한 최신 기술",
            "머신러닝 모델 해석과 해석 가능성",
            "최신 웹 개발 동향과 트렌드",
            "데이터베이스 관리와 운영 가이드",
            "사용자 데이터 보호를 위한 법적 책임",
            "클라우드 인프라스트럭처 구축 방법",
            "실시간 채팅 애플리케이션 개발",
            "웹 성능 최적화를 위한 기법",
            "자연어 처리를 위한 딥러닝 알고리즘",
            "소셜 미디어 분석과 트렌드 예측",
            "DevOps의 원리와 도구들",
            "사용자 인터페이스 디자인 원칙",
            "클라우드 보안을 위한 최신 기술",
            "머신러닝을 활용한 사진 분류 알고리즘",
            "웹 애플리케이션 성능 모니터링 방법",
            "모바일 앱 개발을 위한 크로스 플랫폼 도구",
            "빅데이터 분석을 위한 Apache Hadoop",
            "사이버 위협 대응을 위한 취약점 분석",
            "블록체인의 비즈니스 응용 사례",
            "애자일 테스팅 전략과 방법",
            "웹 애플리케이션 보안의 주요 취약점",
            "클라우드 기반 데이터베이스 솔루션",
            "데이터 마이닝을 위한 기초 알고리즘",
            "소프트웨어 아키텍처 설계 원칙",
            "사용자 경험 설계를 위한 디자인 도구",
            "머신러닝을 활용한 이상 탐지 시스템",
            "웹 개발에서 최적의 성능을 위한 캐싱 전략",
            "모바일 앱 테스팅 자동화 기법",
            "인공지능과 로봇공학의 융합",
            "클라우드 보안을 위한 암호화 기술",
            "데이터 시각화를 위한 JavaScript 라이브러리",
            "사용자 데이터 처리와 개인정보 보호",
            "네트워크 가상화 기술과 솔루션",
            "안드로이드 보안을 위한 취약점 분석",
            "빅데이터 처리를 위한 분산 시스템",
            "소프트웨어 개발 프로젝트 관리 방법",
            "머신러닝을 활용한 추천 시스템",
            "웹 애플리케이션 개발에서의 보안 이슈",
            "소프트웨어 테스팅의 기본 원리와 방법",
            "사용자 경험과 데이터 기반의 디자인",
            "클라우드 기반 AI 플랫폼",
            "웹 개발에서의 반응형 디자인 기법",
            "빅데이터 처리를 위한 NoSQL 데이터베이스",
            "사이버 위협 대응을 위한 실전 시나리오",
            "애자일 소프트웨어 테스팅 전략",
            "웹 보안을 위한 허가 및 인증 방법",
            "머신러닝을 활용한 자연어 처리 애플리케이션",
            "소프트웨어 아키텍처의 종류와 선택 기준",
            "사용자 중심의 인터페이스 디자인 패턴",
            "빅데이터 분석을 위한 기계 학습 알고리즘",
            "안드로이드 앱 보안의 주요 이슈",
            "클라우드 네트워킹과 서비스 구축",
            "데이터 마이닝을 위한 실전 사례",
            "소프트웨어 개발 과정의 품질 관리",
            "사용자 경험 디자인을 위한 테스트 기법",
            "머신러닝을 활용한 음성 인식 시스템",
            "웹 개발에서의 성능 최적화 기법",
            "모바일 앱 개발에서의 성능 향상 전략",
            "사이버 위협 탐지를 위한 AI 기술",
            "빅데이터 시각화를 위한 대시보드 개발",
            "소프트웨어 보안을 위한 악성 코드 분석",
            "사용자 경험 디자인을 위한 워크샵",
            "클라우드 기반 머신러닝 플랫폼",
            "웹 개발에서의 접근성 디자인 가이드",
            "빅데이터 처리를 위한 분산 파일 시스템",
            "소프트웨어 품질 테스팅 전략",
//            "머신러닝과 딥러닝을 활용한 이미지 분석",
//            "사용자 데이터 분석과 행동 예측",
//            "클라우드 보안을 위한 모바일 장치 관리",
//            "데이터베이스 마이그레이션과 업그레이드",
//            "사이버 위협 대응을 위한 침해 사고 분석",
//            "애자일 개발에서의 소프트웨어 테스팅",
//            "웹 보안을 위한 취약점 스캐닝 도구",
//            "머신러닝을 활용한 사기 탐지 시스템",
//            "소프트웨어 개발에서의 버그 추적 및 관리",
//            "사용자 중심의 인터랙션 디자인 원칙",
//            "클라우드 기반 대규모 데이터 처리",
//            "데이터 마이닝을 활용한 고객 세분화 분석",
//            "소프트웨어 테스트 자동화 도구",
//            "사용자 경험 디자인을 위한 웹 분석",
//            "머신러닝을 활용한 자동 번역 시스템",
//            "웹 개발에서의 보안 강화를 위한 가이드",
//            "모바일 앱 개발에서의 퍼포먼스 최적화",
//            "사이버 위협 예방을 위한 네트워크 보안",
//            "빅데이터 분석을 위한 데이터 시각화",
//            "소프트웨어 개발 생명주기 관리",
//            "사용자 경험 디자인을 위한 콘텐츠 전략",
//            "클라우드 기반 IoT 시스템 개발",
//            "웹 개발에서의 반응형 이미지 처리",
//            "머신러닝을 활용한 이벤트 예측 모델",
//            "사용자 데이터 분석과 개인화 서비스",
//            "소프트웨어 보안을 위한 코드 리뷰",
//            "사용자 중심의 인터페이스 디자인 툴",
//            "클라우드 보안을 위한 네트워크 구성"
    };

    private static final String[] BOARD_TITLE_HORROR = {
            "물레방아 소리가 들려오는 밤",
            "어둠 속에서 미소짓는 그림자",
            "길 잃은 자의 유령",
            "저택의 비밀과 저주",
            "실종된 아이의 비밀 일기",
            "위험한 방의 뒷면",
            "사라진 마을의 비밀",
            "절망의 숲에서 들리는 속삭임",
            "무덤에서 깨어난 어둠의 영혼",
            "저주받은 인형의 기억",
            "음산한 의문의 환영",
            "흩어진 신비의 퍼즐 조각",
            "어둠에 떠도는 눈빛",
            "수수께끼의 사람들",
            "끝없는 신비의 문",
            "사라진 마법사의 책",
            "어스름한 시간대의 초대장",
            "괴담 속의 고요한 밤",
            "잃어버린 시간의 미로",
            "죽음의 방 청소부",
            "고통과 광기의 병동",
            "잊혀진 무덤에서 들려오는 불편한 소리",
            "망가진 인형의 시선",
            "헤어진 사랑의 저주",
            "마법사의 저택에서 들리는 미친 웃음",
            "깊은 숲 속 잊혀진 비밀",
            "거울 속에 갇힌 영혼",
            "소리 없는 발걸음의 주인",
            "괴물의 그림자가 떠도는 밤",
            "마법과 불안의 도서관"

    };

    private static final String[] BOARD_TITLE_DOG = {
            "사랑스러운 포메라니안의 모험",
            "활기찬 포메라니안과의 즐거운 시간",
            "충실한 친구, 포메라니안",
            "포메라니안의 사랑과 충성",
            "포메라니안과 함께하는 행복한 일상",
            "포메라니안의 애교와 재롱",
            "소중한 가족, 포메라니안",
            "포메라니안과의 귀여운 순간들",
            "포메라니안과 함께하는 활동적인 놀이",
            "포메라니안의 사랑스러운 미소",
            "포메라니안의 건강과 행복을 위한 팁",
            "포메라니안과 함께하는 산책의 기쁨",
            "포메라니안의 활기찬 에너지",
            "포메라니안의 아기자기한 매력",
            "포메라니안의 사랑스러운 털빠짐",
            "포메라니안과의 달콤한 시간",
            "포메라니안의 귀여운 재롱과 장난",
            "포메라니안의 애교와 사랑스러움",
            "포메라니안과의 행복한 가정생활",
            "포메라니안의 사랑과 보호의 역할",
            "포메라니안과의 멋진 모델 라이프",
            "포메라니안과의 여행과 모험",
            "포메라니안의 사랑스러운 귀여움",
            "포메라니안과의 행복한 추억 만들기",
            "포메라니안의 건강과 관리 팁",
            "포메라니안과의 사랑과 우정",
            "포메라니안의 활기찬 생활과 놀이",
            "포메라니안과 함께하는 펫 테라피",
            "포메라니안의 귀여운 매력에 빠지다",
            "포메라니안과의 행복한 일상 루틴",
            "포메라니안의 애교와 사랑스러운 행동"
    };

    private static final String[] BOARD_TITLE_ISSUE = {
            "용산구에서 벌어진 작은 교통사고",
            "길거리에서 발생한 용산구의 작은 충돌 사건",
            "용산구에서의 작은 화재 사고 이야기",
            "밤길에서 벌어진 용산구의 사소한 도난 사건",
            "작은 싸움이 일어난 용산구의 공원",
            "용산구에서의 작은 건물 무너짐 사고",
            "작은 가게에서 발생한 용산구의 금전 분실 사건",
            "용산구에서의 작은 가로등 고장 사고",
            "주차장에서 벌어진 용산구의 작은 사고",
            "용산구에서의 작은 배낭 도난 사건",
            "작은 폭력 사건이 일어난 용산구의 술집",
            "용산구에서의 작은 자전거 추락 사고",
            "길거리에서 발생한 용산구의 작은 추행 사건",
            "용산구에서의 작은 미용실 화학 물질 사고",
            "작은 무단횡단으로 일어난 용산구의 교통 사고",
            "용산구에서의 작은 쇼핑몰 화재 사건",
            "작은 습격 사건이 일어난 용산구의 은행",
            "용산구에서의 작은 건물 낙하 사고",
            "작은 동물 해방 시위가 벌어진 용산구의 공원",
            "용산구에서의 작은 자전거 도난 사건",
            "길거리에서 발생한 용산구의 작은 신고 사건",
            "용산구에서의 작은 철도 사고 이야기",
            "작은 싸움이 일어난 용산구의 학교",
            "용산구에서의 작은 가로등 고장 사건",
            "주차장에서 벌어진 용산구의 작은 사고",
            "용산구에서의 작은 가방 분실 사건",
            "작은 폭력 사건이 일어난 용산구의 클럽",
            "용산구에서의 작은 자전거 사고 이야기",
            "길거리에서 발생한 용산구의 작은 폭언 사건",
            "용산구에서의 작은 식당 화재 사건"
    };

    private static final String[] BOARD_TITLE_EVENT = {
            "우리 동네 축제에서의 즐거운 하루",
            "동네 공원에서 열린 가족 피크닉 이벤트",
            "동네 마트에서의 할인 행사 이야기",
            "주민들과 함께하는 동네 청소 봉사 활동",
            "동네 도서관에서 열린 독서 모임",
            "동네 공원에서의 야간 커뮤니티 행사",
            "동네 예술 센터에서 열린 작품 전시회",
            "주민 참여형 동네 공연의 묘미",
            "동네 마을 축제에서 만난 다양한 문화",
            "동네 스포츠 클럽의 경기 관람 이벤트"
    };




    @PostConstruct
    public void init() {
        initCityService.initCity();

        initZoneService.initZone_서울특별시();
        initZoneService.initZone_부산광역시();
        initZoneService.initZone_대구광역시();
        initZoneService.initZone_인천광역시();
        initZoneService.initZone_광주광역시();
        initZoneService.initZone_대전광역시();
        initZoneService.initZone_울산광역시();
        initZoneService.initZone_세종특별자치시();
        initZoneService.initZone_경기도();
        initZoneService.initZone_강원특별자치도();
        initZoneService.initZone_충청북도();
        initZoneService.initZone_충청남도();
        initZoneService.initZone_전라북도();
        initZoneService.initZone_전라남도();
        initZoneService.initZone_경상북도();
        initZoneService.initZone_경상남도();
        initZoneService.initZone_제주특별자치도();

        initUserService.initUser();

        initMainCategoryService.initMainCategory();

        initSubCategoryService.initSubCategory();

        initChannelService.initChannel();

        initBoardService.initBoard();

        initBoardCommentService.initBoardComment();

        initReplyCommentService.initReplyComment();

        initBoardLikesService.initBoardLikes();

        initBoardCommentLikesService.initBoardCommentLikes();
        initReplyCommentLikesService.initReplyCommentLikes();
    }

    @Component
    @RequiredArgsConstructor
    static class InitCityService {

        private final CityRepository cityRepository;

        @Transactional
        public void initCity() {
            if (cityRepository.findAll().isEmpty()) {

                City city1 = City.builder()
                        .cityCode("11")
                        .name("서울특별시")
                        .build();
                cityRepository.save(city1);

                City city2 = City.builder()
                        .cityCode("26")
                        .name("부산광역시")
                        .build();
                cityRepository.save(city2);

                City city3 = City.builder()
                        .cityCode("27")
                        .name("대구광역시")
                        .build();
                cityRepository.save(city3);

                City city4 = City.builder()
                        .cityCode("28")
                        .name("인천광역시")
                        .build();
                cityRepository.save(city4);

                City city5 = City.builder()
                        .cityCode("29")
                        .name("광주광역시")
                        .build();
                cityRepository.save(city5);

                City city6 = City.builder()
                        .cityCode("30")
                        .name("대전광역시")
                        .build();
                cityRepository.save(city6);

                City city7 = City.builder()
                        .cityCode("31")
                        .name("울산광역시")
                        .build();
                cityRepository.save(city7);

                City city8 = City.builder()
                        .cityCode("36")
                        .name("세종특별자치시")
                        .build();
                cityRepository.save(city8);

                City city9 = City.builder()
                        .cityCode("41")
                        .name("경기도")
                        .build();
                cityRepository.save(city9);

                City city10 = City.builder()
                        .cityCode("42")
                        .name("강원도")
                        .build();
                cityRepository.save(city10);

                City city11 = City.builder()
                        .cityCode("43")
                        .name("충청북도")
                        .build();
                cityRepository.save(city11);

                City city12 = City.builder()
                        .cityCode("44")
                        .name("충청남도")
                        .build();
                cityRepository.save(city12);

                City city13 = City.builder()
                        .cityCode("45")
                        .name("전라북도")
                        .build();
                cityRepository.save(city13);

                City city14 = City.builder()
                        .cityCode("46")
                        .name("전라남도")
                        .build();
                cityRepository.save(city14);

                City city15 = City.builder()
                        .cityCode("47")
                        .name("경상북도")
                        .build();
                cityRepository.save(city15);

                City city16 = City.builder()
                        .cityCode("48")
                        .name("경상남도")
                        .build();
                cityRepository.save(city16);

                City city17 = City.builder()
                        .cityCode("50")
                        .name("제주특별자치도")
                        .build();
                cityRepository.save(city17);
            }
        }
    }
    @Component
    @RequiredArgsConstructor
    static class InitZoneService {

        private final ZoneRepository zoneRepository;

        @Transactional
        public void initZone_서울특별시() {
            Zone zone1 = Zone.builder()
                    .zoneCode("11110")
                    .name("종로구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone1);

            Zone zone2 = Zone.builder()
                    .zoneCode("11140")
                    .name("중구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone2);

            Zone zone3 = Zone.builder()
                    .zoneCode("11170")
                    .name("용산구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone3);

            Zone zone4 = Zone.builder()
                    .zoneCode("11200")
                    .name("성동구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone4);

            Zone zone5 = Zone.builder()
                    .zoneCode("11215")
                    .name("광진구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone5);

            Zone zone6 = Zone.builder()
                    .zoneCode("11230")
                    .name("동대문구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone6);

            Zone zone7 = Zone.builder()
                    .zoneCode("11260")
                    .name("중랑구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone7);

            Zone zone8 = Zone.builder()
                    .zoneCode("11290")
                    .name("성북구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone8);

            Zone zone9 = Zone.builder()
                    .zoneCode("11305")
                    .name("강북구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone9);

            Zone zone10 = Zone.builder()
                    .zoneCode("11320")
                    .name("도봉구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone10);

            Zone zone11 = Zone.builder()
                    .zoneCode("11350")
                    .name("노원구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone11);

            Zone zone12 = Zone.builder()
                    .zoneCode("11380")
                    .name("은평구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone12);

            Zone zone13 = Zone.builder()
                    .zoneCode("11410")
                    .name("서대문구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone13);

            Zone zone14 = Zone.builder()
                    .zoneCode("11440")
                    .name("마포구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone14);

            Zone zone15 = Zone.builder()
                    .zoneCode("11470")
                    .name("양천구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone15);

            Zone zone16 = Zone.builder()
                    .zoneCode("11500")
                    .name("강서구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone16);

            Zone zone17 = Zone.builder()
                    .zoneCode("11530")
                    .name("구로구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone17);

            Zone zone18 = Zone.builder()
                    .zoneCode("11545")
                    .name("금천구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone18);

            Zone zone19 = Zone.builder()
                    .zoneCode("11560")
                    .name("영등포구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone19);

            Zone zone20 = Zone.builder()
                    .zoneCode("11590")
                    .name("동작구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone20);

            Zone zone21 = Zone.builder()
                    .zoneCode("11620")
                    .name("관악구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone21);

            Zone zone22 = Zone.builder()
                    .zoneCode("11650")
                    .name("서초구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone22);

            Zone zone23 = Zone.builder()
                    .zoneCode("11680")
                    .name("강남구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone23);

            Zone zone24 = Zone.builder()
                    .zoneCode("11710")
                    .name("송파구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone24);

            Zone zone25 = Zone.builder()
                    .zoneCode("11740")
                    .name("강동구")
                    .city(City.builder().cityCode("11").build())
                    .build();
            zoneRepository.save(zone25);
        }

        @Transactional
        public void initZone_부산광역시() {
            Zone zone1 = Zone.builder()
                    .zoneCode("26110")
                    .name("중구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone1);

            Zone zone2 = Zone.builder()
                    .zoneCode("26140")
                    .name("서구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone2);

            Zone zone3 = Zone.builder()
                    .zoneCode("26170")
                    .name("동구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone3);

            Zone zone4 = Zone.builder()
                    .zoneCode("26200")
                    .name("영도구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone4);

            Zone zone5 = Zone.builder()
                    .zoneCode("26230")
                    .name("부산진구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone5);

            Zone zone6 = Zone.builder()
                    .zoneCode("26260")
                    .name("동래구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone6);

            Zone zone7 = Zone.builder()
                    .zoneCode("26290")
                    .name("남구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone7);

            Zone zone8 = Zone.builder()
                    .zoneCode("26320")
                    .name("북구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone8);

            Zone zone9 = Zone.builder()
                    .zoneCode("26350")
                    .name("해운대구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone9);

            Zone zone10 = Zone.builder()
                    .zoneCode("26380")
                    .name("사하구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone10);

            Zone zone11 = Zone.builder()
                    .zoneCode("26410")
                    .name("금정구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone11);

            Zone zone12 = Zone.builder()
                    .zoneCode("26440")
                    .name("강서구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone12);

            Zone zone13 = Zone.builder()
                    .zoneCode("26470")
                    .name("연제구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone13);

            Zone zone14 = Zone.builder()
                    .zoneCode("26500")
                    .name("수영구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone14);

            Zone zone15 = Zone.builder()
                    .zoneCode("26530")
                    .name("사상구")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone15);

            Zone zone16 = Zone.builder()
                    .zoneCode("26710")
                    .name("기장군")
                    .city(City.builder().cityCode("26").build())
                    .build();
            zoneRepository.save(zone16);
        }

        @Transactional
        public void initZone_대구광역시() {
            Zone zone1 = Zone.builder()
                    .zoneCode("27110")
                    .name("중구")
                    .city(City.builder().cityCode("27").build())
                    .build();
            zoneRepository.save(zone1);

            Zone zone2 = Zone.builder()
                    .zoneCode("27140")
                    .name("동구")
                    .city(City.builder().cityCode("27").build())
                    .build();
            zoneRepository.save(zone2);

            Zone zone3 = Zone.builder()
                    .zoneCode("27170")
                    .name("서구")
                    .city(City.builder().cityCode("27").build())
                    .build();
            zoneRepository.save(zone3);

            Zone zone4 = Zone.builder()
                    .zoneCode("27200")
                    .name("남구")
                    .city(City.builder().cityCode("27").build())
                    .build();
            zoneRepository.save(zone4);

            Zone zone5 = Zone.builder()
                    .zoneCode("27230")
                    .name("북구")
                    .city(City.builder().cityCode("27").build())
                    .build();
            zoneRepository.save(zone5);

            Zone zone6 = Zone.builder()
                    .zoneCode("27260")
                    .name("수성구")
                    .city(City.builder().cityCode("27").build())
                    .build();
            zoneRepository.save(zone6);

            Zone zone7 = Zone.builder()
                    .zoneCode("27290")
                    .name("달서구")
                    .city(City.builder().cityCode("27").build())
                    .build();
            zoneRepository.save(zone7);

            Zone zone8 = Zone.builder()
                    .zoneCode("27710")
                    .name("달성군")
                    .city(City.builder().cityCode("27").build())
                    .build();
            zoneRepository.save(zone8);
        }

        @Transactional
        public void initZone_인천광역시() {
            Zone zone1 = Zone.builder()
                    .zoneCode("28110")
                    .name("중구")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone1);

            Zone zone2 = Zone.builder()
                    .zoneCode("28140")
                    .name("동구")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone2);

            Zone zone3 = Zone.builder()
                    .zoneCode("28177")
                    .name("미추홀구")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone3);

            Zone zone4 = Zone.builder()
                    .zoneCode("28185")
                    .name("연수구")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone4);

            Zone zone5 = Zone.builder()
                    .zoneCode("28200")
                    .name("남동구")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone5);

            Zone zone6 = Zone.builder()
                    .zoneCode("28237")
                    .name("부평구")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone6);

            Zone zone7 = Zone.builder()
                    .zoneCode("28245")
                    .name("계양구")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone7);

            Zone zone8 = Zone.builder()
                    .zoneCode("28260")
                    .name("서구")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone8);

            Zone zone9 = Zone.builder()
                    .zoneCode("28710")
                    .name("강화군")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone9);

            Zone zone10 = Zone.builder()
                    .zoneCode("28720")
                    .name("옹진군")
                    .city(City.builder().cityCode("28").build())
                    .build();
            zoneRepository.save(zone10);
        }

        @Transactional
        public void initZone_광주광역시() {
            Zone zone1 = Zone.builder()
                    .zoneCode("29110")
                    .name("동구")
                    .city(City.builder().cityCode("29").build())
                    .build();
            zoneRepository.save(zone1);

            Zone zone2 = Zone.builder()
                    .zoneCode("29140")
                    .name("서구")
                    .city(City.builder().cityCode("29").build())
                    .build();
            zoneRepository.save(zone2);

            Zone zone3 = Zone.builder()
                    .zoneCode("29155")
                    .name("남구")
                    .city(City.builder().cityCode("29").build())
                    .build();
            zoneRepository.save(zone3);

            Zone zone4 = Zone.builder()
                    .zoneCode("29170")
                    .name("북구")
                    .city(City.builder().cityCode("29").build())
                    .build();
            zoneRepository.save(zone4);

            Zone zone5 = Zone.builder()
                    .zoneCode("29200")
                    .name("광산구")
                    .city(City.builder().cityCode("29").build())
                    .build();
            zoneRepository.save(zone5);
        }

        @Transactional
        public void initZone_대전광역시() {
            Zone zone1 = Zone.builder()
                    .zoneCode("30110")
                    .name("동구")
                    .city(City.builder().cityCode("30").build())
                    .build();
            zoneRepository.save(zone1);

            Zone zone2 = Zone.builder()
                    .zoneCode("30140")
                    .name("중구")
                    .city(City.builder().cityCode("30").build())
                    .build();
            zoneRepository.save(zone2);

            Zone zone3 = Zone.builder()
                    .zoneCode("30170")
                    .name("서구")
                    .city(City.builder().cityCode("30").build())
                    .build();
            zoneRepository.save(zone3);

            Zone zone4 = Zone.builder()
                    .zoneCode("30200")
                    .name("유성구")
                    .city(City.builder().cityCode("30").build())
                    .build();
            zoneRepository.save(zone4);

            Zone zone5 = Zone.builder()
                    .zoneCode("30230")
                    .name("대덕구")
                    .city(City.builder().cityCode("30").build())
                    .build();
            zoneRepository.save(zone5);
        }

        @Transactional
        public void initZone_울산광역시() {
            Zone zone1 = Zone.builder()
                    .zoneCode("31110")
                    .name("중구")
                    .city(City.builder().cityCode("31").build())
                    .build();
            zoneRepository.save(zone1);

            Zone zone2 = Zone.builder()
                    .zoneCode("31140")
                    .name("남구")
                    .city(City.builder().cityCode("31").build())
                    .build();
            zoneRepository.save(zone2);

            Zone zone3 = Zone.builder()
                    .zoneCode("31170")
                    .name("동구")
                    .city(City.builder().cityCode("31").build())
                    .build();
            zoneRepository.save(zone3);

            Zone zone4 = Zone.builder()
                    .zoneCode("31200")
                    .name("북구")
                    .city(City.builder().cityCode("31").build())
                    .build();
            zoneRepository.save(zone4);

            Zone zone5 = Zone.builder()
                    .zoneCode("31710")
                    .name("울주군")
                    .city(City.builder().cityCode("31").build())
                    .build();
            zoneRepository.save(zone5);
        }

        @Transactional
        public void initZone_세종특별자치시() {
            Zone zone = Zone.builder()
                    .zoneCode("36110")
                    .name("세종특별자치시")
                    .city(City.builder().cityCode("36").build())
                    .build();
            zoneRepository.save(zone);
        }

        @Transactional
        public void initZone_경기도() {
            Zone zone1 = Zone.builder()
                    .zoneCode("41111")
                    .name("수원시 장안구")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone1);

            Zone zone2 = Zone.builder()
                    .zoneCode("41113")
                    .name("수원시 권선구")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone2);

            Zone zone3 = Zone.builder()
                    .zoneCode("41115")
                    .name("수원시 팔달구")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone3);

            Zone zone4 = Zone.builder()
                    .zoneCode("41117")
                    .name("수원시 영통구")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone4);

            Zone zone5 = Zone.builder()
                    .zoneCode("41131")
                    .name("성남시 수정구")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone5);

            Zone zone6 = Zone.builder()
                    .zoneCode("41133")
                    .name("성남시 중원구")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone6);

            Zone zone7 = Zone.builder()
                    .zoneCode("41135")
                    .name("성남시 분당구")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone7);

            Zone zone8 = Zone.builder()
                    .zoneCode("41150")
                    .name("의정부시")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone8);

            Zone zone9 = Zone.builder()
                    .zoneCode("41171")
                    .name("안양시 동안구")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone9);

            Zone zone10 = Zone.builder()
                    .zoneCode("41190")
                    .name("부천시")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone10);

            Zone zone11 = Zone.builder()
                    .zoneCode("41210")
                    .name("광명시")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone11);

            Zone zone12 = Zone.builder()
                    .zoneCode("41220")
                    .name("평택시")
                    .city(City.builder().cityCode("41").build())
                    .build();
            zoneRepository.save(zone12);

            zoneRepository.save(Zone.builder()
                    .zoneCode("41250")
                    .name("동두천시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41271")
                    .name("안산시 상록구")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41273")
                    .name("안산시 단원구")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41281")
                    .name("고양시 덕양구")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41285")
                    .name("고양시 일산동구")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41287")
                    .name("고양시 일산서구")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41290")
                    .name("과천시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41310")
                    .name("구리시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41360")
                    .name("남양주시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41370")
                    .name("오산시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41390")
                    .name("시흥시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41410")
                    .name("군포시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41430")
                    .name("의왕시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41450")
                    .name("하남시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41461")
                    .name("용인시 처인구")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41463")
                    .name("용인시 기흥구")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41465")
                    .name("용인시 수지구")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41480")
                    .name("파주시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41500")
                    .name("이천시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41550")
                    .name("안성시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41570")
                    .name("김포시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41590")
                    .name("화성시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41610")
                    .name("광주시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41630")
                    .name("양주시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41650")
                    .name("포천시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41670")
                    .name("여주시")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41800")
                    .name("연천군")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41820")
                    .name("가평군")
                    .city(City.builder().cityCode("41").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("41830")
                    .name("양평군")
                    .city(City.builder().cityCode("41").build())
                    .build());

        }

        @Transactional
        public void initZone_강원특별자치도() {
            zoneRepository.save(Zone.builder()
                    .zoneCode("42110")
                    .name("춘천시")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42130")
                    .name("원주시")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42150")
                    .name("강릉시")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42170")
                    .name("동해시")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42190")
                    .name("태백시")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42210")
                    .name("속초시")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42230")
                    .name("삼척시")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42720")
                    .name("홍천군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42730")
                    .name("횡성군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42750")
                    .name("영월군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42760")
                    .name("평창군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42770")
                    .name("정선군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42780")
                    .name("철원군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42790")
                    .name("화천군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42800")
                    .name("양구군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42810")
                    .name("인제군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42820")
                    .name("고성군")
                    .city(City.builder().cityCode("42").build())
                    .build());

            zoneRepository.save(Zone.builder()
                    .zoneCode("42830")
                    .name("양양군")
                    .city(City.builder().cityCode("42").build())
                    .build());
        }

        @Transactional
        public void initZone_충청북도() {
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43111")
                            .name("청주시 상당구")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43112")
                            .name("청주시 서원구")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43113")
                            .name("청주시 흥덕구")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43114")
                            .name("청주시 청원구")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43130")
                            .name("충주시")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43150")
                            .name("제천시")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43720")
                            .name("보은군")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43730")
                            .name("옥천군")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43740")
                            .name("영동군")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43745")
                            .name("증평군")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43750")
                            .name("진천군")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43760")
                            .name("괴산군")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43770")
                            .name("음성군")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("43800")
                            .name("단양군")
                            .city(City.builder().cityCode("43").build())
                            .build()
            );
        }

        @Transactional
        public void initZone_충청남도() {
            // 천안시 동남구
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44131")
                            .name("천안시 동남구")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44133")
                            .name("천안시 서북구")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44150")
                            .name("공주시")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44180")
                            .name("보령시")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44200")
                            .name("아산시")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44210")
                            .name("서산시")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44230")
                            .name("논산시")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44250")
                            .name("계룡시")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44270")
                            .name("당진시")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44710")
                            .name("금산군")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44760")
                            .name("부여군")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44770")
                            .name("서천군")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44790")
                            .name("청양군")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44800")
                            .name("홍성군")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44810")
                            .name("예산군")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("44825")
                            .name("태안군")
                            .city(City.builder().cityCode("44").build())
                            .build()
            );

        }

        @Transactional
        public void initZone_전라북도() {
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45111")
                            .name("전주시 완산구")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45113")
                            .name("전주시 덕진구")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45130")
                            .name("군산시")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45140")
                            .name("익산시")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45180")
                            .name("정읍시")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45190")
                            .name("남원시")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45210")
                            .name("김제시")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45710")
                            .name("완주군")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45720")
                            .name("진안군")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45730")
                            .name("무주군")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45740")
                            .name("장수군")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45750")
                            .name("임실군")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45770")
                            .name("순창군")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45790")
                            .name("고창군")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("45800")
                            .name("부안군")
                            .city(City.builder().cityCode("45").build())
                            .build()
            );
        }

        public void initZone_전라남도() {
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46110")
                            .name("목포시")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46130")
                            .name("여수시")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46150")
                            .name("순천시")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46170")
                            .name("나주시")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46230")
                            .name("광양시")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46710")
                            .name("담양군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46720")
                            .name("곡성군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46730")
                            .name("구례군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46770")
                            .name("고흥군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46780")
                            .name("보성군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46790")
                            .name("화순군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46800")
                            .name("장흥군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46810")
                            .name("강진군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46820")
                            .name("해남군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46830")
                            .name("영암군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46840")
                            .name("무안군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46860")
                            .name("함평군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46870")
                            .name("영광군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46880")
                            .name("장성군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46890")
                            .name("완도군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46900")
                            .name("진도군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("46910")
                            .name("신안군")
                            .city(City.builder().cityCode("46").build())
                            .build()
            );
        }

        public void initZone_경상북도() {
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47111")
                            .name("포항시 남구")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47113")
                            .name("포항시 북구")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47130")
                            .name("경주시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47150")
                            .name("김천시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47170")
                            .name("안동시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47190")
                            .name("구미시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47210")
                            .name("영주시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47230")
                            .name("영천시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47250")
                            .name("상주시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47280")
                            .name("문경시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47290")
                            .name("경산시")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47720")
                            .name("군위군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47730")
                            .name("의성군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47750")
                            .name("청송군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47760")
                            .name("영양군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47770")
                            .name("영덕군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47820")
                            .name("청도군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47830")
                            .name("고령군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47840")
                            .name("성주군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47850")
                            .name("칠곡군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47900")
                            .name("예천군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47920")
                            .name("봉화군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47930")
                            .name("울진군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("47940")
                            .name("울릉군")
                            .city(City.builder().cityCode("47").build())
                            .build()
            );
        }

        public void initZone_경상남도() {
            // 창원시 의창구
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48121")
                            .name("창원시 의창구")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48123")
                            .name("창원시 성산구")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48125")
                            .name("창원시 마산합포구")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48127")
                            .name("창원시 마산회원구")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48129")
                            .name("창원시 진해구")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48170")
                            .name("진주시")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48220")
                            .name("통영시")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48240")
                            .name("사천시")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48250")
                            .name("김해시")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48270")
                            .name("밀양시")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48310")
                            .name("거제시")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48330")
                            .name("양산시")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48720")
                            .name("의령군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48730")
                            .name("함안군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48740")
                            .name("창녕군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48820")
                            .name("고성군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48840")
                            .name("남해군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48850")
                            .name("하동군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48860")
                            .name("산청군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48870")
                            .name("함양군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48880")
                            .name("거창군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("48890")
                            .name("합천군")
                            .city(City.builder().cityCode("48").build())
                            .build()
            );
        }

        public void initZone_제주특별자치도() {
            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("50110")
                            .name("제주시")
                            .city(City.builder().cityCode("50").build())
                            .build()
            );

            zoneRepository.save(
                    Zone.builder()
                            .zoneCode("50130")
                            .name("서귀포시")
                            .city(City.builder().cityCode("50").build())
                            .build()
            );
        }
    }
    @Component
    @RequiredArgsConstructor
    static class InitUserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Transactional
        public void initUser() {
            String encPwd = passwordEncoder.encode("password");

            IntStream.range(0, NAMES.length)
                    .forEach(index -> userRepository.save(
                            User.builder()
                                    .userId("user" + index)
                                    .password(encPwd)
                                    .username(NAMES[index])
                                    .nickname(NAMES[index] + "짱짱맨")
                                    .city(City.builder().cityCode("11").build())
                                    .zone(Zone.builder().zoneCode("11170").build())
                                    .role(Role.USER)
                                    .build()
                    ));

            IntStream.range(100, 1000)
                    .forEach(index -> userRepository.save(
                            User.builder()
                                    .userId("user" + index)
                                    .password(encPwd)
                                    .username("유령회원"+index)
                                    .nickname("유령회원"+index + "짱짱맨")
                                    .city(City.builder().cityCode("11").build())
                                    .zone(Zone.builder().zoneCode("11140").build())
                                    .role(Role.USER)
                                    .build()
                    ));
        }
    }
    @Component
    @RequiredArgsConstructor
    static class InitMainCategoryService {

        private final MainCategoryRepository mainCategoryRepository;

        @Transactional
        public void initMainCategory() {
            if (mainCategoryRepository.findAllByOrderByMainCategoryIdAsc().isEmpty()) {
                mainCategoryRepository.save(MainCategory.builder()
                        .mainCategoryType(MainCategoryType.STORY).build());

                mainCategoryRepository.save(MainCategory.builder()
                        .mainCategoryType(MainCategoryType.FUN).build());

                mainCategoryRepository.save(MainCategory.builder()
                        .mainCategoryType(MainCategoryType.LIFE).build());

                mainCategoryRepository.save(MainCategory.builder()
                        .mainCategoryType(MainCategoryType.NEWS).build());

                mainCategoryRepository.save(MainCategory.builder()
                        .mainCategoryType(MainCategoryType.WORK).build());

                mainCategoryRepository.save(MainCategory.builder()
                        .mainCategoryType(MainCategoryType.RELATIONSHIP).build());

                mainCategoryRepository.save(MainCategory.builder()
                        .mainCategoryType(MainCategoryType.MARRIAGE).build());

            }
        }
    }
    @Component
    @RequiredArgsConstructor
    static class InitSubCategoryService {

        private final SubCategoryRepository subCategoryRepository;
        private final MainCategoryRepository mainCategoryRepository;

        @Transactional
        public void initSubCategory() {
            if (subCategoryRepository.findAll().isEmpty()) {
                Long storyId  = mainCategoryRepository.findByMainCategoryType(MainCategoryType.STORY).getMainCategoryId();
                Long newsId  = mainCategoryRepository.findByMainCategoryType(MainCategoryType.NEWS).getMainCategoryId();
                Long funId  = mainCategoryRepository.findByMainCategoryType(MainCategoryType.FUN).getMainCategoryId();
                Long marriageId  = mainCategoryRepository.findByMainCategoryType(MainCategoryType.MARRIAGE).getMainCategoryId();
                Long lifeId  = mainCategoryRepository.findByMainCategoryType(MainCategoryType.LIFE).getMainCategoryId();
                Long relationshipId  = mainCategoryRepository.findByMainCategoryType(MainCategoryType.RELATIONSHIP).getMainCategoryId();
                Long workId  = mainCategoryRepository.findByMainCategoryType(MainCategoryType.WORK).getMainCategoryId();

                subCategoryRepository.save(SubCategory.builder()
                        .name("사는얘기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(storyId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("10대 이야기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(storyId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("20대 이야기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(storyId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("30대 이야기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(storyId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("40대 이야기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(storyId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("50대 이야기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(storyId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("싱글톡")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(storyId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("훈훈한 이야기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(storyId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("세상에 이런일이")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(newsId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("나억울해요")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(newsId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("묻고 답하기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(newsId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("믿음과 신앙")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(newsId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("개념 상실한 사람들")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(newsId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("배꼽조심")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(funId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("엽기&호러")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(funId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("남편 VS 아내")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(marriageId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("남자들끼리만")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(marriageId)
                                        .build())
                                .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("여자들끼리만")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(marriageId)
                                        .build())
                                .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("결혼/시집/친정")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(marriageId)
                                        .build())
                                .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("맞벌이 부부 이야기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(marriageId)
                                        .build())
                                .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("임신/출산/육아")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(marriageId)
                                        .build())
                                .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("사랑과 이별")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(relationshipId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("해석 남/여")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(relationshipId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("회사생활")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(workId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("취업과 면접")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(workId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("알바 경험담")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(workId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("백수&백조이야기")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(workId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("동물 사랑방")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(lifeId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("여행을 떠나요")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(lifeId)
                                        .build())
                        .build());

                subCategoryRepository.save(SubCategory.builder()
                        .name("포토스토리")
                        .mainCategory(
                                MainCategory.builder()
                                        .mainCategoryId(lifeId)
                                        .build())
                        .build());

            }
        }
    }
    @Component
    @RequiredArgsConstructor
    static class InitChannelService {

        private final ChannelRepository channelRepository;

        @Transactional
        public void initChannel() {
            if (channelRepository.findAll().isEmpty()) {
                channelRepository.save(
                        Channel.builder()
                                .subCategory(SubCategory.builder().subCategoryId(1L).build())
                                .name("개발자모여라")
                                .build());

                channelRepository.save(
                        Channel.builder()
                                .subCategory(SubCategory.builder().subCategoryId(15L).build())
                                .name("무서운이야기")
                                .build());

                channelRepository.save(
                        Channel.builder()
                                .subCategory(SubCategory.builder().subCategoryId(28L).build())
                                .name("포메견주모여요")
                                .build());

                channelRepository.save(
                        Channel.builder()
                                .subCategory(SubCategory.builder().subCategoryId(13L).build())
                                .name("이건좀아니죠")
                                .build());
            }
        }
    }
    @Component
    @RequiredArgsConstructor
    static class InitBoardService {

        private final BoardRepository boardRepository;

        @Transactional
        public void initBoard() {
            if (boardRepository.findAll().isEmpty()) {
                IntStream.range(0, NAMES.length / 2)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE[index])
                                        .content(BOARD_TITLE[index] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.NORMAL)
                                        .mainCategory(MainCategory.builder().mainCategoryId(5L).build())
                                        .subCategory(SubCategory.builder().subCategoryId(25L).build())
//                                .channel(Channel.builder().channelId(1L).build()) 채널 X
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));

                IntStream.range(NAMES.length / 2, NAMES.length)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE[index])
                                        .content(BOARD_TITLE[index] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.NORMAL)
                                        .mainCategory(MainCategory.builder().mainCategoryId(5L).build())
                                        .subCategory(SubCategory.builder().subCategoryId(25L).build())
                                        .channel(Channel.builder().channelId(1L).build())
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));

                IntStream.range(0, 15)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE_HORROR[index])
                                        .content(BOARD_TITLE_HORROR[index] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.NORMAL)
                                        .mainCategory(MainCategory.builder().mainCategoryId(2L).build())
                                        .subCategory(SubCategory.builder().subCategoryId(15L).build())
//                                .channel(Channel.builder().channelId(1L).build()) 채널 X
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));

                IntStream.range(15, 30)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE_HORROR[index])
                                        .content(BOARD_TITLE_HORROR[index] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.NORMAL)
                                        .mainCategory(MainCategory.builder().mainCategoryId(2L).build())
                                        .subCategory(SubCategory.builder().subCategoryId(15L).build())
                                        .channel(Channel.builder().channelId(2L).build())
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));

                IntStream.range(30, 45)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE_DOG[index-30])
                                        .content(BOARD_TITLE_DOG[index-30] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.NORMAL)
                                        .mainCategory(MainCategory.builder().mainCategoryId(3L).build())
                                        .subCategory(SubCategory.builder().subCategoryId(28L).build())
//                                        .channel(Channel.builder().channelId(2L).build())
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));
                IntStream.range(45, 60)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE_DOG[index-30])
                                        .content(BOARD_TITLE_DOG[index-30] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.NORMAL)
                                        .mainCategory(MainCategory.builder().mainCategoryId(3L).build())
                                        .subCategory(SubCategory.builder().subCategoryId(28L).build())
                                        .channel(Channel.builder().channelId(3L).build())
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));

                IntStream.range(60, 75)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE_ISSUE[index-60])
                                        .content(BOARD_TITLE_ISSUE[index-60] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.NORMAL)
                                        .mainCategory(MainCategory.builder().mainCategoryId(4L).build())
                                        .subCategory(SubCategory.builder().subCategoryId(13L).build())
//                                        .channel(Channel.builder().channelId(4L).build())
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));

                IntStream.range(75, 90)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE_ISSUE[index-60])
                                        .content(BOARD_TITLE_ISSUE[index-60] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.NORMAL)
                                        .mainCategory(MainCategory.builder().mainCategoryId(4L).build())
                                        .subCategory(SubCategory.builder().subCategoryId(13L).build())
                                        .channel(Channel.builder().channelId(4L).build())
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));

                IntStream.range(90, 100)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title(BOARD_TITLE_EVENT[index-90])
                                        .content(BOARD_TITLE_EVENT[index-90] + "에 관련된 글 입니다.")
                                        .boardType(BoardType.EVENT)
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11170").build())
                                        .build()
                        ));

                IntStream.range(100, 1000)
                        .forEach(index -> boardRepository.save(
                                Board.builder()
                                        .title("유령회원의글"+index)
                                        .content("유령회원의글"+index + "에 관련된 글 입니다.")
                                        .boardType(BoardType.EVENT)
                                        .user(User.builder().userId("user" + index).build())
                                        .city(City.builder().cityCode("11").build())
                                        .zone(Zone.builder().zoneCode("11140").build())
                                        .build()
                        ));
            }
        }
    }

    @Component
    @RequiredArgsConstructor
    static class InitBoardCommentService {

        private final BoardCommentRepository boardCommentRepository;

        @Transactional
        public void initBoardComment() {
            if (boardCommentRepository.findAll().isEmpty()) {
                for (int i = 0; i < BOARD_TITLE.length; i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(21)) {/*댓글은 21개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*댓글 작성자 랜덤*/
                    }

                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        boardCommentRepository.save(
                                BoardComment.builder()
                                        .content(NAMES[next] + "의 댓글 입니다.")
                                        .user(User.builder().userId("user" + next).build())
                                        .board(Board.builder().boardId((long) i + 1).build())
                                        .build());
                    }
                }

                for (int i = 0; i < BOARD_TITLE_HORROR.length; i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(7)) {/*댓글은 7개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*댓글 작성자 랜덤*/
                    }

                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        boardCommentRepository.save(
                                BoardComment.builder()
                                        .content(NAMES[next] + "의 댓글 입니다.")
                                        .user(User.builder().userId("user" + next).build())
                                        .board(Board.builder().boardId((long) i + 1 + 100).build())
                                        .build());
                    }
                }

                for (int i = 0; i < BOARD_TITLE_DOG.length; i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(7)) {/*댓글은 7개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*댓글 작성자 랜덤*/
                    }

                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        boardCommentRepository.save(
                                BoardComment.builder()
                                        .content(NAMES[next] + "의 댓글 입니다.")
                                        .user(User.builder().userId("user" + next).build())
                                        .board(Board.builder().boardId((long) i + 1 + 130).build())
                                        .build());
                    }
                }

                for (int i = 0; i < BOARD_TITLE_ISSUE.length; i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(7)) {/*댓글은 7개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*댓글 작성자 랜덤*/
                    }

                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        boardCommentRepository.save(
                                BoardComment.builder()
                                        .content(NAMES[next] + "의 댓글 입니다.")
                                        .user(User.builder().userId("user" + next).build())
                                        .board(Board.builder().boardId((long) i + 1 + 160).build())
                                        .build());
                    }
                }

                for (int i = 0; i < BOARD_TITLE_EVENT.length; i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(7)) {/*댓글은 7개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*댓글 작성자 랜덤*/
                    }

                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        boardCommentRepository.save(
                                BoardComment.builder()
                                        .content(NAMES[next] + "의 댓글 입니다.")
                                        .user(User.builder().userId("user" + next).build())
                                        .board(Board.builder().boardId((long) i + 1 + 190).build())
                                        .build());
                    }
                }


            }
        }
    }
    @Component
    @RequiredArgsConstructor
    static class InitReplyCommentService {

        private final ReplyCommentRepository replyCommentRepository;
        private final BoardCommentRepository boardCommentRepository;

        @Transactional
        public void initReplyComment() {
            if (replyCommentRepository.findAll().isEmpty()) {
                List<BoardComment> findAllBoardComent = boardCommentRepository.findAll();
                for (BoardComment boardComment : findAllBoardComent) {
                    Random random = new Random();
                    int randomNum1 = random.nextInt(21); /*0~21미만 랜덤 수*/
                    for (int i = 0; i < randomNum1; i++) {
                        int randomNum2 = random.nextInt(100); /*0~100미만 랜덤 수(랜덤 유저)*/
                        replyCommentRepository.save(
                                ReplyComment.builder()
                                        .content(NAMES[randomNum2]+"의 대댓글 입니다.")
                                        .user(User.builder().userId("user"+randomNum2).build())
                                        .boardComment(BoardComment.builder().boardCommentId(boardComment.getBoardCommentId()).build())
                                        .build()
                        );
                    }
                }
            }
        }
    }

    @Component
    @RequiredArgsConstructor
    static class InitBoardLikesService {
        private final BoardLikesRepository boardLikesRepository;
        @Transactional
        public void initBoardLikes() {
            if (boardLikesRepository.findAll().isEmpty()) {
                for (int i = 0; i < BOARD_TITLE.length; i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(31)) {/*좋아요은 31개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*좋아요 작성자 랜덤*/
                    }
                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        boardLikesRepository.save(
                                BoardLikes.builder()
                                        .user(User.builder().userId("user" + next).build())
                                        .board(Board.builder().boardId((long) i + 1).build())
                                        .build());
                    }
                }

                for (int i = 100; i < 200; i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(10)) {/*좋아요은 10개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*좋아요 작성자 랜덤*/
                    }
                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        boardLikesRepository.save(
                                BoardLikes.builder()
                                        .user(User.builder().userId("user" + next).build())
                                        .board(Board.builder().boardId((long) i + 1).build())
                                        .build());
                    }
                }
            }
        }
    }

    @Component
    @RequiredArgsConstructor
    static class InitBoardCommentLikesService {
        private final BoardCommentLikesRepository boardCommentLikesRepository;
        private final BoardCommentRepository boardCommentRepository;
        @Transactional
        public void initBoardCommentLikes() {
            if (boardCommentLikesRepository.findAll().isEmpty()) {
                List<BoardComment> boardCommentList = boardCommentRepository.findAll();
                for (int i = 0; i < boardCommentList.size(); i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(31)) {/*좋아요은 31개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*좋아요 작성자 랜덤*/
                    }
                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        boardCommentLikesRepository.save(
                                BoardCommentLikes.builder()
                                        .user(User.builder().userId("user" + next).build())
                                        .boardComment(BoardComment.builder().boardCommentId((long) i + 1).build())
                                        .build());
                    }
                }
            }
        }
    }

    @Component
    @RequiredArgsConstructor
    static class InitReplyCommentLikesService {
        private final ReplyCommentLikesRepository replyCommentLikesRepository;
        private final ReplyCommentRepository replyCommentRepository;
        @Transactional
        public void initReplyCommentLikes() {
            if (replyCommentLikesRepository.findAll().isEmpty()) {
                List<ReplyComment> replyCommentList = replyCommentRepository.findAll();
                for (int i = 0; i < replyCommentList.size(); i++) {
                    Set<Integer> numbers = new HashSet<>();
                    Random random = new Random();
                    while (numbers.size() < random.nextInt(31)) {/*좋아요은 31개 미만 랜덤으로*/
                        numbers.add(random.nextInt(NAMES.length)); /*좋아요 작성자 랜덤*/
                    }
                    Iterator<Integer> iter = numbers.iterator();
                    while (iter.hasNext()) {
                        Integer next = iter.next();
                        replyCommentLikesRepository.save(
                                ReplyCommentLikes.builder()
                                        .user(User.builder().userId("user" + next).build())
                                        .replyComment(ReplyComment.builder().replyCommentId((long) i + 1).build())
                                        .build());
                    }
                }
            }
        }
    }
}
