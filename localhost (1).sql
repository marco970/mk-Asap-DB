-- phpMyAdmin SQL Dump
-- version 4.0.4.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 30 PaŸ 2019, 14:40
-- Wersja serwera: 5.6.13
-- Wersja PHP: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES cp1250 */;

--
-- Baza danych: `aukcjotron`
--
CREATE DATABASE IF NOT EXISTS `aukcjotron` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `aukcjotron`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dostawcy`
--

CREATE TABLE IF NOT EXISTS `dostawcy` (
  `dost_name` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dost_act` varchar(40) NOT NULL,
  PRIMARY KEY (`dost_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `dostawcy`
--

INSERT INTO `dostawcy` (`dost_name`, `email`, `dost_act`) VALUES
('Ericsson', '', '0'),
('HP', '', '0'),
('Huawei', '', '0'),
('Nokia', '', '0');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `oferty`
--

CREATE TABLE IF NOT EXISTS `oferty` (
  `bid_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `step_id` int(10) unsigned DEFAULT NULL,
  `dost_name` varchar(40) DEFAULT NULL,
  `offer_value` varchar(40) DEFAULT NULL,
  `pos_step` int(10) NOT NULL,
  `step_value` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`bid_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=165 ;

--
-- Zrzut danych tabeli `oferty`
--

INSERT INTO `oferty` (`bid_id`, `step_id`, `dost_name`, `offer_value`, `pos_step`, `step_value`) VALUES
(125, 0, 'Ericsson', '1000', 1, '100'),
(126, 0, 'HP', '2000', 2, '100'),
(127, 0, 'Huawei', '3000', 4, '100'),
(128, 0, 'Nokia', '2500', 3, '100'),
(129, 1, 'Ericsson', '1000', 1, '100'),
(130, 1, 'HP', '2000', 2, '100'),
(131, 1, 'Huawei', '3000', 4, '100'),
(132, 1, 'Nokia', '2500', 3, '100'),
(133, 2, 'Ericsson', '1000', 1, '100'),
(134, 2, 'HP', '2000', 2, '100'),
(135, 2, 'Huawei', '3000', 4, '100'),
(136, 2, 'Nokia', '2500', 3, '100'),
(137, 3, 'Ericsson', '1000', 1, '100'),
(138, 3, 'HP', '1900', 2, '100'),
(139, 3, 'Huawei', '3000', 4, '100'),
(140, 3, 'Nokia', '2500', 3, '100'),
(141, 4, 'Ericsson', '1000', 1, '100'),
(142, 4, 'HP', '1900', 2, '100'),
(143, 4, 'Huawei', '3000', 4, '100'),
(144, 4, 'Nokia', '2500', 3, '100'),
(145, 5, 'Ericsson', '900', 1, '100'),
(146, 5, 'HP', '1900', 2, '100'),
(147, 5, 'Huawei', '3000', 4, '100'),
(148, 5, 'Nokia', '2500', 3, '100'),
(149, 6, 'Ericsson', '900', 1, '100'),
(150, 6, 'HP', '1800', 2, '100'),
(151, 6, 'Huawei', '3000', 4, '100'),
(152, 6, 'Nokia', '2500', 3, '100'),
(153, 7, 'Ericsson', '900', 1, '100'),
(154, 7, 'HP', '1800', 2, '100'),
(155, 7, 'Huawei', '3000', 4, '100'),
(156, 7, 'Nokia', '2500', 3, '100'),
(157, 8, 'Ericsson', '800', 1, '100'),
(158, 8, 'HP', '1800', 2, '100'),
(159, 8, 'Huawei', '3000', 4, '100'),
(160, 8, 'Nokia', '2500', 3, '100'),
(161, 9, 'Ericsson', '800', 1, '100'),
(162, 9, 'HP', '1800', 2, '100'),
(163, 9, 'Huawei', '2800', 4, '100'),
(164, 9, 'Nokia', '2500', 3, '100');
--
-- Baza danych: `portableu`
--
CREATE DATABASE IF NOT EXISTS `portableu` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `portableu`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lista`
--

CREATE TABLE IF NOT EXISTS `lista` (
  `id_postepowanie` int(11) NOT NULL AUTO_INCREMENT,
  `ZZ` varchar(15) DEFAULT NULL,
  `PZ` varchar(15) DEFAULT NULL,
  `WP` varchar(15) DEFAULT NULL,
  `DK` varchar(15) DEFAULT NULL,
  `Status` varchar(15) DEFAULT NULL,
  `Przedmiot_zakupu` varchar(1000) DEFAULT NULL,
  `Dostawca` varchar(255) DEFAULT NULL,
  `Nazwa` varchar(255) DEFAULT NULL,
  `Tryb_postepowania` varchar(15) DEFAULT NULL,
  `Spolka` varchar(15) DEFAULT NULL,
  `data_ZZ` varchar(15) DEFAULT NULL,
  `data_PZ` varchar(15) DEFAULT NULL,
  `data_WP` varchar(15) DEFAULT NULL,
  `data_DK` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_postepowanie`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=159 ;

--
-- Zrzut danych tabeli `lista`
--

INSERT INTO `lista` (`id_postepowanie`, `ZZ`, `PZ`, `WP`, `DK`, `Status`, `Przedmiot_zakupu`, `Dostawca`, `Nazwa`, `Tryb_postepowania`, `Spolka`, `data_ZZ`, `data_PZ`, `data_WP`, `data_DK`) VALUES
(80, 'ZZ/PLK0005993', 'PZ/0000004863', 'WP/PLK0004909', '', 'zakonczone', 'Modernizacja systemu pomiarowego TEMS - system w samochodach pomiarowych - 4 telefony pomiarowe - aplikacja na desktopa', 'Notel', 'Modernizacja systemu pomiarowego TEMS', 'przetarg', 'PLK', '02.01.2019', '04.01.2019', '25.01.2019', NULL),
(81, 'ZZ/PLI0005949', 'PZ/0000004864', 'WP/PLI0004760', '', 'zakonczone', 'Dostosowanie systemu R-Lite do zmian w otoczeniu biznesowym', 'Touk', 'Dostosowanie systemu R-Lite', 'z wolnej rêki', 'PLI', '21.12.2018', '04.01.2019', '14.01.2019', NULL),
(83, 'ZZ/PLK0005995', 'PZ/0000004876', 'WP/PLK0004692', '', 'zakonczone', 'Przedmiotem zakupu jest szkolenie OTA Gemalto w ramach umowy DK/PLK0000870  Koszt z umowy: 10 kEUR wydatkowanie w 2019', 'Gemalto', 'szkolenie OTA Gemalto', 'przetarg', 'PLK', '28.12.2018', '07.01.2019', '07.01.2019', NULL),
(85, 'ZZ/PLI0005885', 'PZ/0000004752', 'WP/PLI0004830', '', 'zakonczone', 'Okablowanie dla instalacji na Stadionie Narodowym', 'RFCell', 'Okablowanie dla instalacji na Stadionie Narodowym', 'przetarg', 'PLI', '17.12.2018', '04.01.2019', '18.01.2019', NULL),
(86, 'ZZ/PLI0005869', 'PZ/0000004734', 'WP/PLI0006285', '', 'aktywne', 'DWDM Alien Lambda. Proszê o przeprowadzenie postepowania przetargowego na wy³onienie dostawcy sprzêtu DWDM na potrzeby realizacji Alien Lambda.', 'Euvic', 'DWDM Alien Lambda', 'przetarg', 'PLI', '17.12.2018', '04.01.2019', '05.07.2019', NULL),
(90, 'ZZ/PLK0005919', 'PZ/0000004802', 'WP/PLK0004707', '', 'zakonczone', 'Zakup routerów 4G LTE LR77 v2 od producenta Advantech, na podstawie zaakceptowanego Memo z dnia 02/10/2018r ( w za³¹czniku).', 'Advatech', 'Routery dla Plusbank Advatech', 'z wolnej rêki', 'PLK', '19.12.2018', '21.12.2018', '08.01.2019', NULL),
(91, 'ZZ/PLK0005993', 'PZ/0000004919', 'WP/PLK0004910', '', 'zakonczone', 'Modernizacja systemu pomiarowego TEMS - aplikacja na laptopa i na tel od Accuver', 'ACCUVER', 'Accuver Modernizacja TEMS', 'przetarg', 'PLK', '10.01.2019', '10.01.2019', '25.01.2019', NULL),
(93, 'ZZ/PLI0006049', 'PZ/0000005005', '', '', 'aktywne', 'Zawarcie umowy ramowej na zakup radiolinii w pasmie nielicencjonowanym.', '', 'radioninie pasmo nielicencjonowane', 'przetarg', 'PLI', '17.01.2019', '21.01.2019', '', NULL),
(99, 'ZZ/PLK0005990', 'PZ/0000005149', 'WP/PLK0005104', '', 'zakonczone', 'Zakup licencji IoT i SwissQuall dla systemu TEMS Discovery', 'Notel', 'IoT licencje TEMS', 'z wolnej rêki', 'PLK', '04.02.2019', '04.02.2019', '18.02.2019', NULL),
(103, 'ZZ/PLI0006569', 'PZ/0000005313', 'WP/PLI0005165', '', 'zakonczone', 'Rozszerzenie cennika o nowy typ repeatera', 'RFCell', 'nowy repeater do cennika', 'z wolnej rêki', 'PLI', '19.02.2019', '25.02.2019', '26.02.2019', NULL),
(120, 'ZZ/PLI0007125', 'PZ/0000005889', 'WP/PLI0005712', '', 'zakonczone', 'Utrzymanie projektu CTA (wydatek OPEXowy).', 'Touk', 'Utrzymanie CTA(Atoll)', 'z wolnej rêki', 'PLI', '17.04.2019', '07.05.2019', '07.05.2019', NULL),
(126, 'ZZ/PLI0007641', 'PZ/0000006353', 'WP/PLI0006283', '', 'zakonczone', 'DTMA Kathrein 1800-2100-2600 - wzmacniacze antenowe', 'Kathrein', 'Kathrein installacje ant feeder', 'z wolnej rêki', 'PLI', '10.06.2019', '26.06.2019', '05.07.2019', NULL),
(128, 'ZZ/PLI0007738', 'PZ/0000006373', 'WP/PLI0006227', '', 'zakonczone', 'Dodanie do cennika nowych anten Commscope 1L1H i 1L2H', 'RFCell', 'nowe anteny comscope', 'inne', 'PLI', '17.06.2019', '28.06.2019', '28.06.2019', NULL),
(132, 'ZZ/PLK0008106', 'PZ/0000006632', '', '', 'aktywne', 'Dostarczenie w Polkomtel rozwi¹zania RCS + MaaP (Rich Communication Services) w architekturze standalone z opcj¹ migracji do IMS PLK. Zgodnoœæ rozwi¹zania z Universal  Profile w wersji co najmniej 2.3 .', '', 'RCS+MaaP', 'przetarg', 'PLK', '01.08.2019', '02.09.2019', '', ''),
(136, 'ZZ/PLK0008226', 'PZ/0000006829', '', '', 'aktywne', 'Aneks do Umowy Wspó³pracy z Focus Telecom na sprzeda¿y us³ug w ramach platformy Contact Center /// Finansowanie poprzez revshare z przychodów w umowach Klienckich /// W zwi¹zku z uruchomieniem #aplikacji mobilnej# tj dostêp do platformy FCC poprzez telefony z systemem android wymagane prowadzenie dotatkowej pozycji cennikowej, ustalenie zasad oferowani klientom Plus   ', 'Focus Telecom', 'Focus Telecom Aneks', 'z wolnej rêki', 'PLK', '19.08.2019', '05.09.2019', '', NULL),
(137, 'ZZ/PLI0008140', 'PZ/0000006825', 'WP/PLI0006838', '', 'zakonczone', 'Aktualizacja za³¹cznika cenowego z firm¹ Nokia. Dodanie us³ugi demonta¿u feedera hybrydowego o d³ugoœci do 20m.', 'Nokia', 'Nokia feeder 2 demontarz', 'z wolnej rêki', 'PLI', '19.08.2019', '04.09.2019', '20.09.2019', NULL),
(138, 'ZZ/PLI0008148', 'PZ/0000006826', 'WP/PLI0006839', '', 'zakonczone', 'Us³uga przygotowania (uzupe³nienia elementów zdegradowanych np. os³on gumowych) modu³ów radiowych FRHB i FHEB deinstalowanych ze stacji bazowych do ponownego u¿ycia.', 'Nokia', 'Nokia us³uga przystos. modu³ów do ponownego u¿ycia.', 'z wolnej rêki', 'PLI', '19.08.2019', '04.09.2019', '20.09.2019', NULL),
(139, 'ZZ/PLI0008151', 'PZ/0000006843', '', '', 'aktywne', 'Umowa ramowa na dostawê anten i elementów systemów antenowych  bezpoœrednio od producenta (Commsope) zamiast poprzez poœrednika (RFCell)', 'CommScope', 'CommScope ramówka bezpoœrednio', 'z wolnej rêki', 'PLI', '21.08.2019', '05.09.2019', '', NULL),
(140, 'ZZ/PLI0008317', '', '', 'DK/PLI0001609', 'zawieszone', 'Aneks do umowa na IP/MPLS, Zmiana konta.', 'Huawei', 'Hua zmiana konta  IP/MPLS', 'inne', 'PLI', '22.08.2019', '04.09.2019', '04.09.2019', NULL),
(141, 'ZZ/PLI0008312', '', '', 'DK/PLI0001606', 'zawieszone', 'Aneks do umowa na MW, Zmiana konta.', 'Huawei', 'Hua zmiana konta MW', 'inne', 'PLI', '22.08.2019', '04.09.2019', '04.09.2019', NULL),
(142, 'ZZ/PLI0008316', '', '', 'DK/PLI0001608', 'zawieszone', 'Aneks do umowa na DWDM, Zmiana konta.', 'Huawei', 'Hua zmiana konta DWDM', 'inne', 'PLI', '22.08.2019', '04.09.2019', '04.09.2019', NULL),
(143, 'ZZ/PLI0008314', '', '', 'DK/PLI0001607', 'zawieszone', 'Aneks do umowa na CET, Zmiana konta.', 'Huawei', 'Hua zmiana konta CET', 'inne', 'PLI', '22.08.2019', '04.09.2019', '04.09.2019', NULL),
(144, 'ZZ/PLI0008080', 'PZ/0000006814', 'WP/PLI0006688', '', 'zakonczone', 'Modu³y radiowe do sieci Ericsson - zast¹pienie ju¿ wdro¿onej oferty now¹ z doprecyzowanymi korzyœciami projektowymi dotycz¹cymi odkupu przez dostawców starego sprzêtu', 'Ericsson', 'zamiana modu³ów radiowych', 'inne', 'PLI', '02.09.2019', '02.09.2019', '02.09.2019', NULL),
(145, 'ZZ/PLK0008318', 'PZ/0000006948', 'WP/PLK0007079', '', 'aktywne', 'Dwa Routery Cisco 335 Mbps AES 256 IMIX od firmy Atende', 'Atende', 'Dwa routery od Atende', 'z wolnej rêki', 'PLK', '04.09.2019', '20.09.2019', '16.10.2019', ' '),
(146, 'ZZ/PLI0007060', 'PZ/0000006916', 'WP/PLI0006806', '', 'zakonczone', 'kontynuacja zatrudnienia Tomka Olejniczaka w projekcie Proteus/Nowy Cellman', 'BlueSoft', 'Tomek O - kontynuacja', 'z wolnej rêki', 'PLI', '16.09.2019', '16.09.2019', '18.09.2019', NULL),
(148, 'ZZ/PLK0002121', 'PZ/0000001761', 'WP/PLK0004325', '', 'aktywne', 'Rozwi¹zanie SS7 STP (SS7 Signalling Transfer Point)', '', 'STP+NP', 'przetarg', 'PLK', '04.01.2018', '24.01.2018 ', '29.11.2018', ''),
(149, 'ZZ/PLI0008652', 'PZ/0000007095', '', '', 'aktywne', 'Us³ugi programistyczne - "Rozbudowa transmisyjnej bazy danych TRABANT"', 'Touk', 'Trabant - programisci - Touk', 'z wolnej rêki', 'PLI', '30.09.2019', '07.10.2019', '', ' '),
(150, 'ZZ/PLI0008865', 'PZ/0000007234', 'WP/PLI0007156', '', 'aktywne', 'TO - prace programistyczne w firmie BlueSoft w ramach inicjatywy IB-1802, Nowe narzêdzie Cellman - faza 2 "Celina"', 'Bluesoft', 'BlueSoft-Celina', 'z wolnej rêki', 'PLI', '17.10.2019', '21.10.2019', '23.10.2019', ''),
(151, 'ZZ/PLK0008891', 'PZ/0000007206', 'WP/PLK0007138', '', 'aktywne', 'Us³uga wykonywania prób po³¹czeñ testowych do sieci ruchomej Polkomtel Sp. z o.o. spoza EOG w 2020 r. w zwi¹zku z wykrywaniem nadu¿yæ polegaj¹cych na podmianie numeru inicjuj¹cego po³¹czenie w celu obni¿enia op³aty MTR za zakoñczenie po³¹czenia w sieci Polkomtel. Us³uga wykonywana dziennie. Œrednia liczba testów - 260/dzieñ. Kwota op³aty miesiêcznej 4500 EUR. Rocznie 54 000 EUR ~ 235 000 PLN zgodnie z przyjêtym kursem EUR (4,35 z³/EUR) i zaokr¹gleniu do tysiêcy z³. Testy s¹ kontynuowane z firm¹Sigos od 2016 r. Zamówienie bêdzie sk³adane do umowy #Agreement between Polkomtel Sp. z o.o. and SIGOS, signed on 03 February 2015#', 'Sigos', '2020-SigosTesty', 'z wolnej rêki', 'PLK', '17.10.2019', '18.10.2019', '21.10.2019', ' '),
(152, 'ZZ/PLI0008865', 'PZ/0000007238', 'WP/PLI0007157', '', 'aktywne', 'MR - prace programistyczne w firmie BlueSoft w ramach inicjatywy IB-1802, Nowe narzêdzie Cellman - faza 2 "Celina"', 'BlueSoft', 'BlueSoft Celina - MR', 'z wolnej rêki', 'PLI', '21.10.2019', '21.10.2019', '23.10.2019', ''),
(153, 'ZZ/NET0001851', '', '', '', 'zawieszone', '', '', 'Z/1851/2019 - utrzymanie Juniper', 'przetarg', 'NET', '23.10.2019', '', '', NULL),
(154, 'Z/1851/2019', '', '', '', 'aktywne', 'Utzymanie Juniper', '', 'Utrzymanie Juniper dlaNetii', 'przetarg', '', '23.10.2019', '', '', NULL),
(155, 'ZZ/PLI0008950', '', '', '', 'aktywne', 'Oferta na sektor LTE TDD B42', 'Nokia', 'LTE TDD dla Mielca', 'z wolnej rêki', 'PLI', '28.10.2019', '', '', NULL),
(156, 'ZZ/PLI0008899', '', '', '', 'aktywne', 'Oferta na sektor LTE TDD B42', 'Nokia', 'LTE TDD Nokia', 'z wolnej rêki', 'PLI', '28.10.2019', '', '', NULL),
(157, 'ZZ/PLI0008882', '', '', '', 'aktywne', 'Aktualizacja (nowy sprzêt, renegocjacje warunków œwiadczenia us³ug wspomagaj¹cych) i rozszerzenie (alien lambda)  cennika DWDM z umowy ramowej, podpisanie aneksu umowy DWDM', 'Huawei', 'DWDM aneks', 'z wolnej rêki', 'PLI', '29.10.2019', '', '', NULL),
(158, 'Z/1398/2019', '', '', '', 'aktywne', 'Przed³u¿enie umowy utrzymaniowej Netii z Noki¹', 'Nokia', 'UU Nokia Netia', 'z wolnej rêki', '', '30.10.2019', '', '', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `notes`
--

CREATE TABLE IF NOT EXISTS `notes` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `note` varchar(2000) DEFAULT NULL,
  `id_postepowanie` int(11) NOT NULL,
  `date_open` varchar(12) DEFAULT NULL,
  `date_modified` varchar(12) DEFAULT NULL,
  `is_open` tinyint(1) NOT NULL,
  PRIMARY KEY (`note_id`),
  KEY `id_postepowanie` (`id_postepowanie`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=158 ;

--
-- Zrzut danych tabeli `notes`
--

INSERT INTO `notes` (`note_id`, `note`, `id_postepowanie`, `date_open`, `date_modified`, `is_open`) VALUES
(126, 'tworzenie umowy:\n- odpytaæ TR\n- posprawdzaæ samemu\n', 86, '27.09.2019', '04.10.2019', 1),
(127, 'czekam na zakoñczenie testów\nna 1.10 zaplanowane spotkanie, zakladam, ¿e uda sie zakoñczyæ ten etap, \n===================4.10\npewtarzamy testy Huawei, next meeting - 10.10', 93, '27.09.2019', '04.10.2019', 0),
(130, 'przygotowanie wymagañ:\n- maj¹ byæ na 4.10\n- wysy³ka 7.10\n============== 4.10\nwci¹¿ trwaj¹ warsztaty, wymagañ jeszcze nie ma, \nmoje przygotowane\n', 132, '27.09.2019', '04.10.2019', 0),
(131, 'kolejny etap to aukcja\n- odœwierzyæ oferty\n- przygotowaæ narzêdzie\n- przygotowaæ scope', 93, '27.09.2019', '27.09.2019', 0),
(132, 'lista oferentów:\n- zacz¹æ pracowaæ....', 132, '27.09.2019', '27.09.2019', 0),
(133, 'Oferta Huawei w zakresie DE wygl¹da na prawie akceptowaln¹:\nPlan:\n- ja z DS przygotowujê projekt aneksu\n- przekazujemy go do Huawei \n- ostateczne spotkanie - u SK p mo¿e jeszcze urwiemy trochê kasy.\n', 86, '30.09.2019', '14.10.2019', 1),
(134, 'Jeœli przyjmiemy ofertê Huawei:\n- rekomendacja?\n- umowa z Euvic?\n- co jeszcze do aneksu z Huawei?', 86, '30.09.2019', '14.10.2019', 1),
(135, 'Czekamy na decyzjê T. Mudy:\nzaakceptuje propozycjê Waltera?\n==== 4.10\nW koñcu Walte zaakceptowa³ nasze rz¹dania \n- plan min.  To by³o 1.10\n', 148, '30.09.2019', '04.10.2019', 1),
(136, 'Zakres aneksu do DWDM:\n1.      Cap w okresie obowi¹zywania umowy, niezale¿nie od liczby sprzêtu w sieci\n2.      Poziom cap jest ….\n3.      W zakresie us³ug wsparcia Huawei nie mo¿e wypowiedzieæ umowy wczeœniej ni¿ po 7 latach (to mamy gwarantowane). PLK infra – bez zmian (mo¿emy zrezygnowaæ..)\n4.      Kwestie zakresu us³ug.\n5.      Warunki obowi¹zuj¹ od 1.01.2020\n6.      Ew je¿eli tego ju¿ nie ma: je¿eli zaterminujemy œwiadczeni us³ug w ci¹gu roku to op³ata CAP bêdzie proporcjonalna do okresu œwiadczenia us³ug.\n', 86, '30.09.2019', '04.10.2019', 1),
(137, 'AR - co dalej z akceptacj¹ rekomendacji Euvic?\n', 86, '04.10.2019', '04.10.2019', 0),
(138, 'AR - co dalej z wk³adem do aneksu z Huawei?\n=======14.10\n- sytuacja?\n     - AR, TO, WK..........\n- ZZ na aneks?', 86, '04.10.2019', '14.10.2019', 0),
(140, 'porównaæ ceny\n============7.10\nznalaz³em tylko 5, czy 6 pozycji,\nw ka¿dym przypadku ceny Commscope lepsze\n\nzada³em pytanie Konradowi, co s¹dzi o cenniku\nCommscope, odp po 10.10.\n\n=================14.10\nCommscope ma uzupe³niæ cennik o pozycje przes³ane przez Konrada...\npotem ewentualnie pogadam o cenach\n==================22.10\nCennik uzupe³niony, traeba sprawdziæ i poprosiæ o uzupe³nienie opisów', 139, '04.10.2019', '23.10.2019', 0),
(141, 'czekam na draft umowy\n\n=============23.10\ndostarczony', 139, '04.10.2019', '23.10.2019', 1),
(142, '- Utworzyæ postêpowanie\n- Oferta?', 149, '04.10.2019', '04.10.2019', 0),
(144, 'czy aneks skoñczony?\njak tak - procesowaæ postêpowanie, wynik i potemDK\n\n', 136, '04.10.2019', '04.10.2019', 0),
(145, 'Czekam a¿ Piotr uzgodni aneks, wtedy go przeprocesujê.\n\n', 136, '04.10.2019', '04.10.2019', 0),
(146, 'Czekam na feedback Atende \n  - ma byæ na pon. 7.10\n=================== 7.10\nna razie Wicher nie jest w stanie dostarczyæ oferty\n', 145, '04.10.2019', '07.10.2019', 0),
(147, 'równolegle z testami przygotowaæ umowê:\n1. Czego brakuje\n	- oprócz za³¹cznik 1\n	\n2. Spotkanie 8.10 na 13.00; Dubrownik\n	- jak pogadaæ o tym za³¹czniku?\n\n3. Zastanowiæ sie, czy nie wys³aæ do prawnika\n	- sprawdziæ, czy mo¿e ju¿ nie by³o wys³ane? :)\n========= 8.10\nad 3 - by³o, Mi³osz Kiziñski weryfikowa³...\n\n', 148, '04.10.2019', '09.10.2019', 1),
(148, 'uzupe³niæ postêpowanie\n- za³¹czniki', 149, '07.10.2019', '07.10.2019', 0),
(149, 'Zapytanie do Fabisiaka z NCR', 145, '07.10.2019', '07.10.2019', 0),
(150, 'czekam na GO from Grzegorz Kawulski', 149, '08.10.2019', '08.10.2019', 0),
(151, 'umowa oprócz za³ 1:\n- feedback od Roberta Dêbkowskiego\n- weryfikacja z Zespo³em', 148, '08.10.2019', '08.10.2019', 0),
(152, 'Za³¹cznik techniczny - oddzielny w¹tek\n- Ja - wywaliæ "Huawei Response" - asap\n- Rafa³ - uwagi do 18.10\n- Artur - uwagi do zakoñczenia testów\n- S³awek - Andrzej siê weŸmie po zakoñczeniu \ntestów.', 148, '09.10.2019', '09.10.2019', 0),
(153, 'wolna rêka od pocz¹tku\nw przypadku koniecznoœci wziêcia kogoœ z innej\nfirmy - bêdzie aplikacja na woln¹ rêkê na poziomie\nWP', 150, '17.10.2019', '17.10.2019', 0),
(154, 'Uwagi do umowy:\n1. uwagi zasadnicze - moje - przes³ane 23.10\n(DDP, zmiêkczanie, prawo i rozstrzyganie sporów, inne w tekscie) - czekam na odniesienie sie \nS³awka\n2. zweryfikowaæ za³¹czniki - Konrad i Artur \nAlinowski\n', 139, '23.10.2019', '23.10.2019', 0),
(155, 'od Jurka - uzyskaæ zamówienie dostawcze ', 151, '23.10.2019', '23.10.2019', 0),
(156, 'czêœæ utrzymaniowa:\n   - status?\n\nczêœæ zakupowa:\n  - status?', 157, '29.10.2019', '29.10.2019', 0),
(157, 'dopisaæ do umowy postanowienia dot. us³ug\nwspomagaj¹cych DWDM (op³at za te us³ugi)\n\n', 148, '29.10.2019', '29.10.2019', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `time_sheet`
--

CREATE TABLE IF NOT EXISTS `time_sheet` (
  `entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `id_postepowanie` int(11) NOT NULL,
  `sap_nr` varchar(16) NOT NULL,
  `date_entry` varchar(12) NOT NULL,
  `time_passed` int(11) NOT NULL,
  PRIMARY KEY (`entry_id`),
  KEY `id_postepowanie` (`id_postepowanie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=130 ;

--
-- Zrzut danych tabeli `time_sheet`
--

INSERT INTO `time_sheet` (`entry_id`, `id_postepowanie`, `sap_nr`, `date_entry`, `time_passed`) VALUES
(73, 144, 'ZZ/PLI0008080', '02.09.2019', 1),
(74, 144, 'PZ/0000006814', '02.09.2019', 1),
(75, 137, 'PZ/0000006825', '04.09.2019', 2),
(76, 138, 'PZ/0000006826', '04.09.2019', 2),
(77, 141, 'DK/PLI0001606', '04.09.2019', 1),
(78, 142, 'DK/PLI0001608', '05.09.2019', 1),
(79, 143, 'DK/PLI0001607', '05.09.2019', 1),
(80, 140, 'DK/PLI0001609', '05.09.2019', 2),
(81, 139, 'PZ/0000006843', '05.09.2019', 1),
(82, 146, 'ZZ/PLI0007060', '16.09.2019', 1),
(83, 146, 'PZ/0000006916', '16.09.2019', 1),
(84, 93, 'PZ/0000005005', '05.09.2019', 2),
(85, 86, 'PZ/0000004734', '05.09.2019', 2),
(86, 86, 'PZ/0000004734', '06.09.2019', 3),
(87, 143, 'DK/PLI0001607', '06.09.2019', 1),
(88, 141, 'DK/PLI0001606', '06.09.2019', 1),
(89, 86, 'PZ/0000004734', '09.09.2019', 2),
(90, 139, 'PZ/0000006843', '10.09.2019', 3),
(91, 139, 'PZ/0000006843', '09.09.2019', 3),
(92, 86, 'PZ/0000004734', '13.09.2019', 2),
(93, 93, 'PZ/0000005005', '19.09.2019', 1),
(94, 93, 'PZ/0000005005', '12.09.2019', 1),
(95, 93, 'PZ/0000005005', '26.09.2019', 1),
(96, 86, 'PZ/0000004734', '18.09.2019', 2),
(97, 86, 'PZ/0000004734', '25.09.2019', 2),
(98, 139, 'PZ/0000006843', '17.09.2019', 1),
(99, 139, 'PZ/0000006843', '24.09.2019', 1),
(100, 139, 'PZ/0000006843', '30.09.2019', 1),
(101, 146, 'PZ/0000006916', '02.09.2019', 1),
(102, 146, 'PZ/0000006916', '12.09.2019', 1),
(103, 146, 'PZ/0000006916', '25.09.2019', 1),
(104, 148, 'ZZ/PLK0002121', '30.09.2019', 1),
(105, 148, 'PZ/0000001761', '30.09.2019', 2),
(106, 149, 'ZZ/PLI0008652', '30.09.2019', 1),
(107, 149, 'PZ/0000007095', '07.10.2019', 1),
(108, 86, 'PZ/0000004734', '09.10.2019', 4),
(109, 93, 'PZ/0000005005', '10.10.2019', 2),
(110, 93, 'PZ/0000005005', '03.10.2019', 2),
(111, 139, 'PZ/0000006843', '08.10.2019', 2),
(112, 145, 'PZ/0000006948', '16.10.2019', 1),
(113, 150, 'ZZ/PLI0008865', '17.10.2019', 1),
(114, 151, 'ZZ/PLK0008891', '17.10.2019', 1),
(115, 151, 'PZ/0000007206', '18.10.2019', 2),
(116, 150, 'PZ/0000007234', '21.10.2019', 2),
(117, 152, 'ZZ/PLI0008865', '21.10.2019', 1),
(118, 152, 'PZ/0000007238', '21.10.2019', 2),
(119, 153, 'ZZ/NET0001851', '23.10.2019', 1),
(121, 154, 'Z/1851/2019', '25.10.2019', 2),
(122, 154, 'Z/1851/2019', '14.10.2019', 3),
(123, 154, 'Z/1851/2019', '21.10.2019', 3),
(124, 155, 'ZZ/PLI0008950', '28.10.2019', 1),
(125, 154, 'Z/1851/2019', '28.10.2019', 1),
(126, 156, 'ZZ/PLI0008899', '28.10.2019', 1),
(127, 157, 'ZZ/PLI0008882', '29.10.2019', 1),
(128, 158, 'Z/1398/2019', '30.10.2019', 1),
(129, 154, 'Z/1851/2019', '30.10.2019', 2);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`id_postepowanie`) REFERENCES `lista` (`id_postepowanie`);

--
-- Ograniczenia dla tabeli `time_sheet`
--
ALTER TABLE `time_sheet`
  ADD CONSTRAINT `time_sheet_ibfk_1` FOREIGN KEY (`id_postepowanie`) REFERENCES `lista` (`id_postepowanie`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
