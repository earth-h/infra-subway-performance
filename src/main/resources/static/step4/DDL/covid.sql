CREATE TABLE `covid` (
  `id` bigint(20) NOT NULL,
  `hospital_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `programmer_id` bigint(20) DEFAULT NULL,
  `hospital_type_code` varchar(255) DEFAULT NULL,
  `city_code_hospital` int(11) DEFAULT NULL,
  `hospital_region_code` varchar(255) DEFAULT NULL,
  `available_extra_rooms_in_hospital` int(11) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `ward_type` varchar(255) DEFAULT NULL,
  `ward_facility_code` varchar(255) DEFAULT NULL,
  `bed_grade` double DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `city_code_patient` varchar(255) DEFAULT NULL,
  `type_of_admission` varchar(255) DEFAULT NULL,
  `severity_of_Illness` varchar(255) DEFAULT NULL,
  `visitors_with_patient` int(11) DEFAULT NULL,
  `admission_deposit` double DEFAULT NULL,
  `stay` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `stay_index` (`stay`),
  KEY `member_hospital_index` (`member_id`,`hospital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4