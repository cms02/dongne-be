package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.city.repository.CityRepository;
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

@Profile({"local","dev"})
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitUserService initUserService;

    @PostConstruct
    public void init() {
        initUserService.initUser();
        initUserService.initCity();
        initUserService.initZone_서울특별시();
        initUserService.initZone_부산광역시();
        initUserService.initZone_대구광역시();
        initUserService.initZone_인천광역시();
        initUserService.initZone_광주광역시();
        initUserService.initZone_대전광역시();
        initUserService.initZone_울산광역시();
        initUserService.initZone_세종특별자치시();
        initUserService.initZone_경기도();
        initUserService.initZone_강원특별자치도();
        initUserService.initZone_충청북도();
        initUserService.initZone_충청남도();
        initUserService.initZone_전라북도();
        initUserService.initZone_전라남도();
        initUserService.initZone_경상북도();
        initUserService.initZone_경상남도();
        initUserService.initZone_제주특별자치도();
    }

    @Component
    @RequiredArgsConstructor
    static class InitUserService {

        private final UserRepository memberRepository;
        private final CityRepository cityRepository;
        private final ZoneRepository zoneRepository;
        private final PasswordEncoder passwordEncoder;

        @Transactional
        public void initUser() {
            String encPwd1 = passwordEncoder.encode("password1");
            String encPwd2 = passwordEncoder.encode("password2");

            User user1 = User.builder()
                    .userId("userId1")
                    .username("홍길동")
                    .city(City.builder().cityCode("11").build())
                    .zone(Zone.builder().zoneCode("11200").build())
                    .password(encPwd1)
                    .nickname("성동구깍두기")
                    .role(Role.USER)
                    .build();
            User user2 = User.builder()
                    .userId("userId2")
                    .username("곽준빈")
                    .city(City.builder().cityCode("11").build())
                    .zone(Zone.builder().zoneCode("11170").build())
                    .password(encPwd2)
                    .nickname("용산구날라리")
                    .role(Role.USER)
                    .build();

            memberRepository.save(user1);
            memberRepository.save(user2);
        }

        @Transactional
        public void initCity() {
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
}
