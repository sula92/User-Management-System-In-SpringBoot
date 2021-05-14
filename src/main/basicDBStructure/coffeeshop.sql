
CREATE TABLE `customer` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `item` (
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `qtyOnHand` int(11) NOT NULL,
  `unitPrice` decimal(19,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `order` (
  `id` varchar(255) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `customerId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `orderdetail` (
  `itemCode` varchar(255) NOT NULL,
  `orderId` varchar(255) NOT NULL,
  `qty` int(11) NOT NULL,
  `unitPrice` decimal(19,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `item`
  ADD PRIMARY KEY (`code`);


ALTER TABLE `order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcustomer` (`customerId`);


ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`itemCode`,`orderId`),
  ADD KEY `FKorder` (`orderId`);



ALTER TABLE `order`
  ADD CONSTRAINT `FKckicmtwun913u69pha7agsgpd` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`);


ALTER TABLE `orderdetail`
  ADD CONSTRAINT `FK21x4a3ee3h5uwombp0n7cayng` FOREIGN KEY (`orderId`) REFERENCES `order` (`id`),
  ADD CONSTRAINT `FKtogd3d0kvb3mreeh4pn7qox19` FOREIGN KEY (`itemCode`) REFERENCES `item` (`code`);
COMMIT;


