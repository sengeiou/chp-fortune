DELETE FROM tz.t_gl_email_template;
INSERT INTO tz.t_gl_email_template(
            id, template_name, template_type, template_content, dict_status, 
            description)
    VALUES ('4', '邮箱验证', '4', '尊敬的信和客户：<br>您好，您的邮箱验证码为<font color="#FF0000" size="5">{#pin#}</font>，此验证码用于信和财富预留邮箱验证，请勿转发。
祝您生活愉快！详询400-090-1199', '1', 
            '邮箱验证');


INSERT INTO tz.t_gl_email_template(
            id, template_name, template_type, template_content, dict_status, 
            description)
    VALUES ('1', '新债权文件首期', '1', '尊敬的信和客户：<br>
 &nbsp;&nbsp;&nbsp;&nbsp; 您好，感谢您选择信和公司的咨询服务，参考信和公司的推荐进行资金的出借增值。附件为您首期《债权转让及受让协议》，如有任何问题可联系您的专属客户经理。为保证您的该笔投资顺利出借，请您于time准备好划扣款项，感谢您的配合！信和竭诚为您提供优质的服务！
<br>
<br>
<br>
<br>
联系我们：<br>
Tel:400-090-1199<br>
Add:北京市朝阳区东三环北路甲19号SOHO嘉盛中心3603室<br>
Web： www.creditharmony.cn<br>
注意事项：<br>
1、 如果您无法打开PDF格式的文件，请您下载并安装PDF阅读器。<br>
2、 如果您打开PDF格式的文件后为乱码的情况，您可以通过软件更新的方式实现恢复正常阅读。<br>
3、 如发生未收到我公司电子邮件的情况，可能是以下原因造成：<br>
请检查您的电子邮箱容量是否已满<br>
您的电子邮箱是否设置为拒绝接收企业邮箱发送的邮件<br>
您的电子邮箱将邮件屏蔽或将邮件转入了垃圾邮件文件夹<br>
发送邮件时网络堵塞', '1',  '《债权转让及受让协议》');


INSERT INTO tz.t_gl_email_template(
            id, template_name, template_type, template_content, dict_status, 
            description)
    VALUES ('2', '新债权文件非首期', '2', '尊敬的信和客户：<br>
 &nbsp;&nbsp;&nbsp;&nbsp;您好，感谢您选择信和公司的咨询服务，参考信和公司的推荐进行资金的出借增值。附件为您非首期《债权转让及受让协议》及《资金出借情况报告》，如有任何问题可联系您的专属客户经理。
<br>
<br>
<br>
<br>
联系我们：<br>
Tel:400-090-1199<br>
Add:北京市朝阳区东三环北路甲19号SOHO嘉盛中心3601室<br>
Web： www.creditharmony.cn<br>
注意事项：<br>
1、 如果您需要纸质版账单，请与您的客户经理联系。<br>
2、 现推行电子签章认证技术，推荐您下载并安装Adobe ReaderPDF阅读器进行查看。<br>
3、 如果您打开Adobe ReaderPDF格式的文件后为乱码的情况，您可以通过软件更新的方式实现恢复正常阅读。<br>
4、 如发生未收到我公司电子邮件的情况，可能是以下原因造成：<br>
请检查您的电子邮箱是否已满<br>
您的电子邮箱是否设置为拒绝接收企业邮箱发送的邮件<br>
您的电子邮箱将邮件屏蔽或将邮件转入了垃圾邮件文件夹<br>
发送邮件时网络堵塞', '1', 
            '《债权转让及受让协议》');

INSERT INTO tz.t_gl_email_template(
            id, template_name, template_type, template_content, dict_status, 
            description)
    VALUES ('3', '收款确认书', '3', '尊敬的信和客户：<br>
 &nbsp;&nbsp;&nbsp;&nbsp;您好，感谢您选择信和公司的咨询服务，附件为您的《收款确认书》，如有任何问题可联系您的专属客户经理。感谢您的配合！信和竭诚为您提供优质的服务！
<br>
<br>
<br>
<br>
联系我们：<br>
Tel:400-090-1199<br>
Add:北京市朝阳区东三环北路甲19号SOHO嘉盛中心3601室<br>
Web:www.creditharmony.cn<br>
注意事项：<br>
1、 如果您需要纸质版账单，请与您的客户经理联系。<br>
2、 现推行电子签章认证技术，推荐您下载并安装Adobe ReaderPDF阅读器进行查看。<br>
3、 如果您打开Adobe ReaderPDF格式的文件后为乱码的情况，您可以通过软件更新的方式实现恢复正常阅读。<br>
4、 如发生未收到我公司电子邮件的情况，可能是以下原因造成：<br>
请检查您的电子邮箱容量是否已满 <br>
您的电子邮箱是否设置为拒绝接收企业邮箱发送的邮件 <br>
您的电子邮箱将邮件屏蔽或将邮件转入了垃圾邮件文件夹 <br>
发送邮件时网络堵塞。<br>', '1', 
            '《收款确认书》');

delete from tz."t_gl_tz_product";
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'金信盈','1','1',12,12,'1','0,1,2,3','出借金额*11/100+出借金额',1.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),100.00000,NULL,'89',1.02000,1.00000,11.00000,11.00000,'123');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'金信宝','0','1',24,24,'2','0,1,3,2','出借金额*14/100,出借金额*14/100+出借金额,出借金额*28/100+出借金额*14/100*12.68/100+出借金额',1.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),100.00000,'0','90',1.10000,NULL,13.20000,NULL,NULL);
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'信和月增','1','1',24,NULL,'1','7,8,9','回款金额=出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额',50000.00000,'gt101',TO_DATE('2016-04-15 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 11:45:07','YYYY-MM-DD HH24:MI:SS'),150.00000,NULL,'66',1.08300,1.08300,10.20000,13.00000,'两年期每月返息');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'信和年聚','1','1',24,NULL,'0','7,8,9','出借金额*年化收益率*2+出借金额',50000.00000,'gt101',TO_DATE('2016-04-15 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 11:46:13','YYYY-MM-DD HH24:MI:SS'),150.00000,NULL,'67',1.10000,1.10000,12.00000,13.00000,'两年返本息');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'双季盈','1','1',6,6,'0','1,2,3,5,9,14','出借金额*年化收益率/12*出借期限+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:50:52','YYYY-MM-DD HH24:MI:SS'),50.00000,NULL,'84',1.02000,0.85000,12.24000,9.00000,'6个月短期投入 实现较高收益');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'月满盈','1','1',1,1,'0','1,2,3,5,9','出借金额*匹配利率/100/出借天数*计息天数+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:51:42','YYYY-MM-DD HH24:MI:SS'),8.00000,NULL,'86',0.48300,0.48300,5.76000,5.80000,'30天短期投入 实现较高收益');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'年年盈','1','1',12,12,'0','1,2,3,5','出借金额*11/100+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:51:56','YYYY-MM-DD HH24:MI:SS'),100.00000,NULL,'85',1.02000,1.00000,12.24000,11.00000,'年年盈');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'信和宝','1','1',24,24,'1','0,1,2,3','出借金额*14/100,
 出借金额*14/100+出借金额,出借金额*28/100+出借金额*14/100*12.68/100+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:52:11','YYYY-MM-DD HH24:MI:SS'),100.00000,NULL,'87',1.10000,1.10000,13.20000,14.00000,'一年一返息或两年返本息');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'信和宝C','1','1',24,24,'1','1,5','出借金额*13.6/100/2,出借金额*13.6/100/2+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:52:59','YYYY-MM-DD HH24:MI:SS'),150.00000,NULL,'93',1.10000,1.10000,13.20000,13.60000,'半年一返息');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'信和宝B','1','1',24,24,'0','1,5','出借金额*129.78/100',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:53:12','YYYY-MM-DD HH24:MI:SS'),150.00000,NULL,'92',1.10000,1.10000,13.20000,14.89000,'两年返本息');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'信和宝A','1','1',24,24,'1','1,5','出借金额*14/100,出借金额*14/100+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:53:28','YYYY-MM-DD HH24:MI:SS'),150.00000,NULL,'91',1.10000,1.10000,13.20000,14.00000,'一年一返息');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'季度盈','1','1',3,3,'0','1,2,3,5,9','出借金额*年化收益率/12*出借期限+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:53:42','YYYY-MM-DD HH24:MI:SS'),25.00000,NULL,'83',1.02000,0.85000,9.60000,8.00000,'3个月短期投入 实现较高收益');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'月邮赢','1','0',12,12,'1','5,9','出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:53:59','YYYY-MM-DD HH24:MI:SS'),100.00000,NULL,'80',1.02000,1.00000,9.60000,9.60000,'1');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'年年金','1','1',12,12,'0','8,9,10','出借金额*年化收益率/12*出借期限+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:54:15','YYYY-MM-DD HH24:MI:SS'),100.00000,NULL,'88',0.85000,0.85000,9.60000,7.00000,'渠道合作');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'月息通','2','1',12,12,'1','1,2,3,5,9','出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:54:33','YYYY-MM-DD HH24:MI:SS'),100.00000,NULL,'82',1.02000,1.00000,9.60000,12.00000,'每月回收利息 本金持续出借');
INSERT INTO tz.t_gl_tz_product (product_name,product_type_code,product_status,product_periods,product_closedate,product_is_back,product_treaty,product_formula,start_invest_amount,create_by,create_time,modify_by,modify_time,product_discountrate,ymy_flag,product_code,matching_rate_upper,matching_rate_lower,old_year_profit,new_year_profit,remark) VALUES (
'信和通','2','1',12,12,'0','0,1,2,3,5,9','出借金额*年化收益率/12*出借期限+出借金额',50000.00000,'admin',TO_DATE('2016-01-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-15 13:54:50','YYYY-MM-DD HH24:MI:SS'),100.00000,NULL,'81',1.02000,1.00000,9.60000,12.68000,'通过循环出借方式  获取较高预期年收益');
delete from tz.t_tz_product_matching_rate;
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'ac1c69e9fa5642988cedd859181b247f','83','1','60000246',TO_DATE('2016-06-13 14:15:15','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:15:15','YYYY-MM-DD HH24:MI:SS'),'1',0.85000,0.85000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),99999999.00000,1.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'48151b4059e34e3998192973eb859a45','86','1','60000246',TO_DATE('2016-06-13 14:15:40','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:15:40','YYYY-MM-DD HH24:MI:SS'),'1',0.48300,0.48300,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),99999999.00000,1.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'3ad7d55df58b473e8d15b55cf1e3b190','84','1','60000246',TO_DATE('2016-06-13 14:16:52','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:16:52','YYYY-MM-DD HH24:MI:SS'),'1',0.85000,0.85000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),99999999.00000,1.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'6eec752edbd54c889733a4021c18cacc','82','1','60000246',TO_DATE('2016-06-13 14:18:34','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:18:34','YYYY-MM-DD HH24:MI:SS'),'1',0.80000,0.80000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),99999.00000,1.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'fe5b240aa9094f99b3ad423c9fdf07ef','67','1','60000246',TO_DATE('2016-06-13 14:18:46','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:18:46','YYYY-MM-DD HH24:MI:SS'),'1',1.00000,1.00000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),499999.00000,1.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'b009d62671ce444b930703e927aeb09c','82','1','60000246',TO_DATE('2016-06-13 14:19:40','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:19:40','YYYY-MM-DD HH24:MI:SS'),'1',0.83000,0.83000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),299999.00000,100000.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'7039ed76a4e5472a82749d250694d52b','67','1','60000246',TO_DATE('2016-06-13 14:19:57','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:19:57','YYYY-MM-DD HH24:MI:SS'),'1',1.10000,1.10000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),99999999.00000,500000.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'31cdddf6e2d14d00aba46ed8ae8f86f4','82','1','60000246',TO_DATE('2016-06-13 14:20:18','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:20:18','YYYY-MM-DD HH24:MI:SS'),'1',0.86000,0.86000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),499999.00000,300000.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'0096cdb2df7647869b7dbb66365ad52b','82','1','60000246',TO_DATE('2016-06-13 14:20:56','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:20:56','YYYY-MM-DD HH24:MI:SS'),'1',0.90000,0.90000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),999999.00000,500000.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'53639ab818a643cb8fc8d9bfaf64fb84','81','1','60000246',TO_DATE('2016-06-13 14:20:59','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:20:59','YYYY-MM-DD HH24:MI:SS'),'1',0.85000,0.85000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),299999.00000,1.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'9c88f9d1bdd34612a598e14bb1a04dc5','81','1','60000246',TO_DATE('2016-06-13 14:21:34','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:21:34','YYYY-MM-DD HH24:MI:SS'),'1',1.00000,1.00000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),99999999.00000,300000.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'd2cdc740183b4eab866943381f408f24','82','1','60000246',TO_DATE('2016-06-13 14:21:43','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:21:43','YYYY-MM-DD HH24:MI:SS'),'1',0.95000,0.95000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),99999999.00000,1000000.00000,NULL,NULL);
INSERT INTO tz.t_tz_product_matching_rate (id,product_code,use_flag,create_by,create_time,modify_by,modify_time,bill_type,matching_rate_upper,matching_rate_lower,apply_lendday_upper,apply_lendday_lower,apply_lend_money_upper,apply_lend_money_lower,matching_bill_day,matching_interest_start) VALUES (
'92264401d566476fa42f13d604d59925','66','1','60000246',TO_DATE('2016-06-13 14:30:16','YYYY-MM-DD HH24:MI:SS'),'60000246',TO_DATE('2016-06-13 14:30:16','YYYY-MM-DD HH24:MI:SS'),'1',0.85000,0.85000,TO_DATE('2016-06-30','YYYY-MM-DD'),TO_DATE('2016-04-01','YYYY-MM-DD'),99999999.00000,1.00000,NULL,NULL);
delete from tz.t_tz_platform_goto_rule;
INSERT INTO tz.t_tz_platform_goto_rule (id,platform_id,platform_rule_name,platform_rule,status,create_by,create_time,modify_by,modify_time) VALUES (
'd8a622216ad245b8a6cf7b8daa5d7b7c','0','富友平台','0,2,','1','admin',TO_DATE('2016-05-24 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_platform_goto_rule (id,platform_id,platform_rule_name,platform_rule,status,create_by,create_time,modify_by,modify_time) VALUES (
'654fff34b2ec48109c705439e545a2bd','2','中金平台','2','3','admin',TO_DATE('2016-05-24 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_platform_goto_rule (id,platform_id,platform_rule_name,platform_rule,status,create_by,create_time,modify_by,modify_time) VALUES (
'7c4b794b07a142029afcf4760558ba14','1','好易联平台','1,2','1','admin',TO_DATE('2016-05-24 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-06-01 00:00:00','YYYY-MM-DD HH24:MI:SS'));
delete from gl.t_gl_platform_bank;
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','105','105','通联','建设银行',5000000,20000000,NULL,'0','2301105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','302','302','通联','中信银行',5000000,20000000,NULL,'0','2301302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','0','102','0102','中金','工商银行',1000000,10000000,NULL,'1','2201102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','102','0102','富友','工商银行',2000000,2000000,NULL,'1','2000102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','105','0105','富友','建设银行',5000000,10000000,NULL,'1','2000105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','104','104','好易联','中国银行',1000000,1000000,NULL,'0','2101104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','104','0104','富友','中国银行',5000000,1000000000,NULL,'1','3000104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','102','102','好易联','工商银行',5000000,5000000,NULL,'0','3101102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','103','103','好易联','农业银行',1000000,2000000,NULL,'0','2101103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','303','303','好易联','光大银行',50000000,2500000000,NULL,'0','2101303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','306','306','好易联','广发银行',50000000,2500000000,NULL,'0','2101306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','307','307','好易联','平安银行',50000000,2500000000,NULL,'0','2101307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','308','308','好易联','招商银行',50000000,2500000000,NULL,'0','2101308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','309','309','好易联','兴业银行',50000000,2500000000,NULL,'0','2101309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','310','310','好易联','上海浦发银行',500000,500000,NULL,'0','2101310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','403','403','好易联','邮政储蓄',5000000,250000000,NULL,'0','2101403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','666','301','好易联','交通银行',10000000,110000000,NULL,'0','2101666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','102','102','中金','工商银行',100000,100000,NULL,'1','2211102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','104','0104','富友','中国银行',5000000,1000000000,NULL,'1','2000104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','302','0302','富友','中信银行',50000000,1000000000,NULL,'1','2000302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','303','0303','富友','光大银行',50000000,1000000000,NULL,'1','2000303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','304','0304','富友','华夏银行',50000000,1000000000,NULL,'1','2000304');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','306','0306','富友','广发银行',50000000,1000000000,NULL,'1','2000306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','307','0307','富友','平安银行',50000000,1000000000,NULL,'1','2000307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','308','0308','富友','招商银行',100000,100000,NULL,'1','2000308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','309','0309','富友','兴业银行',1000000,1000000,NULL,'1','2000309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','310','0310','富友','上海浦发银行',4999999,4999999,NULL,'1','2000310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','403','0403','富友','邮政储蓄',50000000,1000000000,NULL,'1','2000403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','103','103','中金','农业银行',9999999999,9999999999,NULL,'1','2211103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','1','104','104','中金','中国银行',50000000,200000000,NULL,'1','2201104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','104','104','中金','中国银行',9999999999,9999999999,NULL,'1','2211104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','1','105','105','中金','建设银行',20000000,50000000,NULL,'1','2201105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','105','105','中金','建设银行',9999999999,9999999999,NULL,'1','2211105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','1','302','302','中金','中信银行',500000,5000000,NULL,'1','2201302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','302','302','中金','中信银行',9999999999,9999999999,NULL,'1','2211302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','0','303','303','中金','光大银行',20000000,50000000,NULL,'1','2200303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','303','303','中金','光大银行',9999999999,9999999999,NULL,'1','2211303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','1','305','305','中金','民生银行',50000000,200000000,NULL,'1','2201305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','305','305','中金','民生银行',9999999999,9999999999,NULL,'1','2211305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','1','306','306','中金','广发银行',50000000,200000000,NULL,'1','2201306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','306','306','中金','广发银行',9999999999,9999999999,NULL,'1','2211306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','1','307','307','中金','平安银行',1000000,20000000,NULL,'1','2201307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','307','307','中金','平安银行',9999999999,9999999999,NULL,'1','2211307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','0','308','308',NULL,NULL,1000000,10000000,NULL,'1','2230800');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','0','308','308',NULL,NULL,1000000,10000000,NULL,'1','2230810');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','1','309','309','中金','兴业银行',1000000,20000000,NULL,'1','2201309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','309','309','中金','兴业银行',9999999999,9999999999,NULL,'1','2211309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','1','666','301','中金','交通银行',2000000,2000000,NULL,'1','2201666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','1','1','666','301','中金','交通银行',9999999999,9999999999,NULL,'1','2211666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','103','103','通联','农业银行',5000000,20000000,NULL,'1','2301103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','103','103','通联','农业银行',20000000,300000000,NULL,'1','2311103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','104','104','通联','中国银行',1000000,1000000,NULL,'1','2301104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','104','104','通联','中国银行',20000000,300000000,NULL,'1','2311104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','105','105','通联','建设银行',20000000,300000000,NULL,'1','2311105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','302','302','通联','中信银行',20000000,300000000,NULL,'1','2311302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','303','303','通联','光大银行',5000000,20000000,NULL,'1','2301303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','303','303','通联','光大银行',20000000,300000000,NULL,'1','2311303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','304','304','通联','华夏银行',5000000,20000000,NULL,'1','2301304');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','304','304','通联','华夏银行',20000000,300000000,NULL,'1','2311304');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','305','305','通联','民生银行',5000000,20000000,NULL,'1','2301305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','305','305','通联','民生银行',20000000,300000000,NULL,'1','2311305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','307','307','通联','平安银行',5000000,20000000,NULL,'1','2301307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','307','307','通联','平安银行',20000000,300000000,NULL,'1','2311307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','308','308','通联','招商银行',5000000,20000000,NULL,'1','2301308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','308','308','通联','招商银行',20000000,300000000,NULL,'1','2311308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','309','309','通联','兴业银行',5000000,20000000,NULL,'1','2301309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','309','309','通联','兴业银行',20000000,300000000,NULL,'1','2311309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','310','310','通联','上海浦发银行',4999999,19999999,NULL,'1','2301310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','310','310','通联','上海浦发银行',20000000,300000000,NULL,'1','2311310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','403','403','通联','邮政储蓄',500000,20000000,NULL,'1','2301403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','403','403','通联','邮政储蓄',20000000,300000000,NULL,'1','2311403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','0','1','666','301','通联','交通银行',5000000,20000000,NULL,'1','2301666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','3','1','1','666','301','通联','交通银行',20000000,300000000,NULL,'1','2311666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','102','0102','富友','工商银行',2000000,2000000,NULL,'1','3000102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','103','0103','富友','农业银行',200000,1000000,NULL,'1','3000103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','105','0105','富友','建设银行',5000000,10000000,NULL,'1','3000105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','302','0302','富友','中信银行',50000000,1000000000,NULL,'1','3000302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','303','0303','富友','光大银行',50000000,1000000000,NULL,'1','3000303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','304','0304','富友','华夏银行',50000000,1000000000,NULL,'1','3000304');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','306','0306','富友','广发银行',50000000,1000000000,NULL,'1','3000306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','307','0307','富友','平安银行',50000000,1000000000,NULL,'1','3000307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','308','0308','富友','招商银行',100000,100000,NULL,'1','3000308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','309','0309','富友','兴业银行',1000000,1000000,NULL,'1','3000309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','310','0310','富友','上海浦发银行',4999999,4999999,NULL,'1','3000310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','0','0','0','403','0403','富友','邮政储蓄',50000000,1000000000,NULL,'1','3000403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','102','102','中金','工商银行',5000000,5000000,NULL,'1','3200102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','102','102','中金','工商银行',5000000,5000000,NULL,'1','3201102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','102','102','中金','工商银行',9999999999,9999999999,NULL,'1','3211102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','103','103','中金','农业银行',20000000,50000000,NULL,'1','3200103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','103','103','中金','农业银行',20000000,50000000,NULL,'1','3201103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','103','103','中金','农业银行',9999999999,9999999999,NULL,'1','3211103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','104','104','中金','中国银行',50000000,200000000,NULL,'1','3201104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','104','104','中金','中国银行',9999999999,9999999999,NULL,'1','3211104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','105','105','中金','建设银行',20000000,50000000,NULL,'1','3200105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','105','105','中金','建设银行',20000000,50000000,NULL,'1','3201105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','105','105','中金','建设银行',9999999999,9999999999,NULL,'1','3211105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','302','302','中金','中信银行',50000000,200000000,NULL,'1','3200302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','302','302','中金','中信银行',500000,5000000,NULL,'1','3201302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','302','302','中金','中信银行',9999999999,9999999999,NULL,'1','3211302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','303','303','中金','光大银行',20000000,50000000,NULL,'1','3200303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','303','303','中金','光大银行',1000000,20000000,NULL,'1','3201303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','303','303','中金','光大银行',9999999999,9999999999,NULL,'1','3211303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','305','305','中金','民生银行',50000000,200000000,NULL,'1','3200305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','305','305','中金','民生银行',50000000,200000000,NULL,'1','3201305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','305','305','中金','民生银行',9999999999,9999999999,NULL,'1','3211305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','306','306','中金','广发银行',50000000,200000000,NULL,'1','3200306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','306','306','中金','广发银行',50000000,200000000,NULL,'1','3201306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','306','306','中金','广发银行',9999999999,9999999999,NULL,'1','3211306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','307','307','中金','平安银行',50000000,200000000,NULL,'1','3200307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','307','307','中金','平安银行',1000000,20000000,NULL,'1','3201307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','307','307','中金','平安银行',9999999999,9999999999,NULL,'1','3211307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','309','309','中金','兴业银行',5000000,5000000,NULL,'1','3200309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','309','309','中金','兴业银行',1000000,20000000,NULL,'1','3201309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','309','309','中金','兴业银行',9999999999,9999999999,NULL,'1','3211309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','1','1','666','301','中金','交通银行',9999999999,9999999999,NULL,'1','3211666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','103','103','通联','农业银行',5000000,20000000,NULL,'1','3300103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','103','103','通联','农业银行',5000000,20000000,NULL,'1','3301103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','103','103','通联','农业银行',20000000,300000000,NULL,'1','3311103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','104','104','通联','中国银行',1000000,1000000,NULL,'1','3300104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','104','104','通联','中国银行',1000000,1000000,NULL,'1','3301104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','104','104','通联','中国银行',20000000,300000000,NULL,'1','3311104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','105','105','通联','建设银行',20000000,300000000,NULL,'1','3311105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','302','302','通联','中信银行',5000000,20000000,NULL,'1','3300302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','302','302','通联','中信银行',5000000,20000000,NULL,'1','3301302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','302','302','通联','中信银行',20000000,300000000,NULL,'1','3311302');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','303','303','通联','光大银行',5000000,20000000,NULL,'1','3300303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','303','303','通联','光大银行',5000000,20000000,NULL,'1','3301303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','303','303','通联','光大银行',20000000,300000000,NULL,'1','3311303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','304','304','通联','华夏银行',5000000,20000000,NULL,'1','3300304');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','304','304','通联','华夏银行',5000000,20000000,NULL,'1','3301304');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','304','304','通联','华夏银行',20000000,300000000,NULL,'1','3311304');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','305','305','通联','民生银行',5000000,20000000,NULL,'1','3300305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','305','305','通联','民生银行',5000000,20000000,NULL,'1','3301305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','305','305','通联','民生银行',20000000,300000000,NULL,'1','3311305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','307','307','通联','平安银行',5000000,20000000,NULL,'1','3300307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','307','307','通联','平安银行',5000000,20000000,NULL,'1','3301307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','307','307','通联','平安银行',20000000,300000000,NULL,'1','3311307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','308','308','通联','招商银行',1000000,20000000,NULL,'1','3300308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','308','308','通联','招商银行',5000000,20000000,NULL,'1','3301308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','308','308','通联','招商银行',20000000,300000000,NULL,'1','3311308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','309','309','通联','兴业银行',5000000,20000000,NULL,'1','3300309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','309','309','通联','兴业银行',5000000,20000000,NULL,'1','3301309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','309','309','通联','兴业银行',20000000,300000000,NULL,'1','3311309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','310','310','通联','上海浦发银行',4999999,19999999,NULL,'1','3300310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','310','310','通联','上海浦发银行',4999999,19999999,NULL,'1','3301310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','310','310','通联','上海浦发银行',20000000,300000000,NULL,'1','3311310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','403','403','通联','邮政储蓄',500000,20000000,NULL,'1','3301403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','403','403','通联','邮政储蓄',20000000,300000000,NULL,'1','3311403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','1','1','666','301','通联','交通银行',20000000,300000000,NULL,'1','3311666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','104','104','中金','中国银行',5000000,5000000,NULL,'1','3200104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','305','305','好易联','民生银行',50000000,2500000000,NULL,'0','2101305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','104','104','好易联','中国银行',1000000,1000000,NULL,'0','3100104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','104','104','好易联','中国银行',1000000,1000000,NULL,'0','3101104');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','103','103','好易联','农业银行',5000000,250000000,NULL,'0','3101103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','303','303','好易联','光大银行',50000000,2500000000,NULL,'0','3100303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','303','303','好易联','光大银行',50000000,2500000000,NULL,'0','3101303');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','305','305','好易联','民生银行',50000000,2500000000,NULL,'0','3100305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','305','305','好易联','民生银行',50000000,2500000000,NULL,'0','3101305');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','306','306','好易联','广发银行',50000000,2500000000,NULL,'0','3100306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','306','306','好易联','广发银行',50000000,2500000000,NULL,'0','3101306');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','307','307','好易联','平安银行',50000000,2500000000,NULL,'0','3100307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','103','103','好易联','农业银行',5000000,250000000,NULL,'0','3100103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','307','307','好易联','平安银行',50000000,2500000000,NULL,'0','3101307');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','308','308','好易联','招商银行',5000000,250000000,NULL,'0','3100308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','308','308','好易联','招商银行',50000000,2500000000,NULL,'0','3101308');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','309','309','好易联','兴业银行',5000000,250000000,NULL,'0','3100309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','309','309','好易联','兴业银行',50000000,2500000000,NULL,'0','3101309');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','310','310','好易联','上海浦发银行',500000,500000,NULL,'0','3100310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','310','310','好易联','上海浦发银行',500000,500000,NULL,'0','3101310');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','403','403','好易联','邮政储蓄',5000000,250000000,NULL,'0','3100403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','403','403','好易联','邮政储蓄',5000000,250000000,NULL,'0','3101403');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','666','301','好易联','交通银行',10000000,110000000,NULL,'0','3100666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','1','666','301','好易联','交通银行',10000000,110000000,NULL,'0','3101666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','1','0','0','102','102','好易联','工商银行',5000000,5000000,NULL,'0','3100102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','1','666','301','中金','交通银行',2000000,2000000,NULL,'0','3201666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','308','308',NULL,NULL,10000000,200000000,NULL,'1','3230800');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','0','105','105','通联','建设银行',500000,20000000,NULL,'1','3300105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','105','105','通联','建设银行',500000,20000000,NULL,'1','3301105');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','3','0','1','666','301','通联','交通银行',5000000,20000000,NULL,'0','3301666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'3','2','0','0','666','301','中金','交通银行',2000000,2000000,NULL,'1','3200666');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','1','0','1','102','102','好易联','工商银行',2000000,2000000,NULL,'0','2101102');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','0','0','0','103','0103','富友','农业银行',1000000,2000000,NULL,'1','2000103');
INSERT INTO gl.t_gl_platform_bank (sys_id,platform_id,deduct_flag,deduct_type,bank_id,bank_cd,platform_name,bank_name,single_limit_money,day_limit_money,day_limit_times,status,id) VALUES (
'2','2','0','0','103','103','中金','农业银行',1000000,8000000,NULL,'1','2201103');
delete from tz.t_tz_creditor_config;
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'8d7185bec4a14275815478fc703cbb7a',7,7,NULL,NULL,'1','1','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'8baa2becd60e4ca59eab90e43fc43786',19,15,NULL,NULL,'1','1','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'0df9e410d0104a169a438a4c857fa348',11,10,NULL,NULL,'1','1','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'fdd894f7e48647349d7841be3fa1502d',7,7,NULL,NULL,'2','1','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'666ce3f7d6cd487fbd02736678687dd0',11,10,NULL,NULL,'2','1','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'509a989fa4ff4d8c913ba3b097dc1e38',15,15,NULL,NULL,'1','1','admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'292daef576d044519f01febc33085653',19,18,NULL,NULL,'1','1','admin',TO_DATE('2016-05-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-16 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'911ebe6f89cb4b7f915ee5df38a286cb',23,23,NULL,NULL,'2','1','admin',TO_DATE('2016-05-18 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-18 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'ebdef7bbb95e486ca8dfe1342be37ea6',19,10,NULL,NULL,'1','1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'befd8a7474d64cc5b537ae97bd38e9e8',19,25,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'20f09fcb37134d13a9978e105499c0a9',19,10,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'404c056dae4c43d19a5dfc1d72ad291d',19,7,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'a7881c39ab4d46ca84e2cb7c35235684',19,3,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'3f6209ba43954b83884974afb762fc01',19,15,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'20e871279cae482c97e8a0ad5d062fc2',19,30,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'7be4ecfb5b8c464199b35298b4f82200',19,18,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'4363251ddb514d2193c3d2ff58aaa00f',19,23,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'93dc2346b04f418ca2f8c44d506642fe',19,10,NULL,NULL,'2','1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'927914e0597a4f9fb83317d3aba16af3',4,3,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'4e9ac22bb6c8425aa33daaa014ba286e',4,30,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'b367c8adad114b799090b0f85965d952',4,25,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'dd5a07eed8ea4ea89e6f66b3eeaeb85c',4,3,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'925f7ee7c3eb43ae81f65776b4dde8cb',4,30,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'ae2620c6eddb40e3addb46dea8af82a8',4,25,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'5616cac65cbb40ef8b23036ba7181cea',7,3,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'6beb870346014c65aaea77ecd40abefb',7,30,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'035d6a78ec59474ebeea4c7456568095',7,3,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'1fe7b08c5a8d41029e9e0fc060cff710',7,30,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'45a0cb136768489486d926e7af240b16',11,7,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'ee427ad602f846e98a05f7c83242eecc',11,3,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'4a70b0f409e6461e928c07daa9c14f4d',11,7,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'd8fac0917f2a42b99a0ad527e3f06243',11,3,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'18dc2800a177434c9eb89a25e0235f2f',15,15,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'05742160c2ee4afc840e360deb7b6b94',19,30,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'2b27fe2c0ec045e1acc007af94d64d82',19,25,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'f41ea9e3312b4570ad0d11fbd36696d2',19,23,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'd6cd1504825e44008ac8b550a5721e9c',19,3,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'144795f729f84104bd484daf115753e5',19,7,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'67ba702b3d0d49eab7b7444cf4ac871f',19,18,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'1c7bcd230af648a78f18d4b86f32f3e7',19,15,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'3948d6103efc49c9b3a3ef910c69bc06',23,25,NULL,NULL,'2','-1','admin',TO_DATE('2016-05-18 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'1590efb4cc63448693bd061b865e42ee',23,23,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'5d950597788f4cf8bea0277dc571ecf4',26,7,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'1785fe16ee3746bfbb4d6a10a4262ade',26,25,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'a5a69d93381e4ad78518e0601dd4c8a1',26,25,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'25d6d0c5ad7d4302b2168481f7c91c1a',30,3,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'23b8dc4348574e72b7da2051097ec4d6',30,7,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'f519bed35c1e411a939b749e5a42e920',30,10,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'745123592a4a45f0bec4c62b686c3fbc',30,15,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'a4564332432c49cab7b0797cfe1771f0',30,18,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'c4da6d53404742c4abc262d2ea0653c3',30,30,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'8434e6b0bfde44c98ba7fb0abb9d3ee0',30,30,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'fd8e0eaf775c4482ad9fd643b5e4e864',19,30,NULL,NULL,'1','-1','admin',TO_DATE('2016-05-21 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-22 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'447506a18764497dbb23567b4a714186',26,18,NULL,NULL,'2','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-25 23:27:57','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'acbfbae0f8a84211b4e5b92ef7152dae',26,23,NULL,NULL,'1','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-25 23:28:02','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'6fc7d8f066a24c22a9e401aa08bfc039',26,23,NULL,NULL,'2','0','admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-25 23:28:06','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'a5adbc2404944ddbb481ce8bc001d5a7',26,18,NULL,NULL,'1','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-25 23:28:10','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'7724b1a46907431684527d5702540e21',30,25,NULL,NULL,'1','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-30 14:15:49','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'1a5a327aa0444dd1a78c8445b0923e71',30,25,NULL,NULL,'2','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-30 14:15:58','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'41df294fc1ba43f7ae82824ec76ee09e',30,23,NULL,NULL,'2','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-30 14:16:13','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'9c8f35f563a848be8ad585a6ce30564a',30,23,NULL,NULL,'1','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-30 14:16:26','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'3672ec9457de4ab088681475c2b460bd',15,7,NULL,NULL,'1','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-31 17:22:40','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'422081c0fdda48f3ad9199d3dd81eb86',15,7,NULL,NULL,'2','0','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-31 17:22:52','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'e52d576a4caa4b0cb4abb4ade816980c',15,10,NULL,NULL,'2','0','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-31 17:23:02','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'333eabc8dc9d4092baff6827da2b04ad',15,10,NULL,NULL,'1','0','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-31 17:23:11','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'47a73c9acfe149feb955b683c782f1bd',23,15,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-06-08 14:00:44','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'c26a00dc57384739908088139f49e6ad',23,15,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-06-08 14:01:04','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'09a8e7a15bc54f3ba9567fde9db5762c',23,18,NULL,NULL,'1','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-06-08 14:01:21','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditor_config (id,config_credit_day,config_repay_day,config_is_first,config_is_use,dictconfig_status,dict_config_zdr,create_by,create_time,modify_by,modify_time) VALUES (
'92f7dd10bc704235b65ce86d7274690f',23,18,NULL,NULL,'2','1','admin',TO_DATE('2016-05-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-06-08 14:01:36','YYYY-MM-DD HH24:MI:SS'));

delete from tz.t_tz_platform;
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10000','夏靖','342623197003010319','招商银行','6225880153886065','5',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10001','夏靖','342623197003010319','中信银行','6226980701534933','5',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10002','夏靖','342623197003010319','光大银行','6226620206427098','5',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10003','夏靖','342623197003010319','工商银行','6212260200020849382','5',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10004','夏靖','342623197003010319','建设银行','6227000016510033763','5',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10005','夏靖','342623197003010319','华夏银行(新)','6230230010016840','5',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10006','资金托管',NULL,NULL,NULL,'4',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10007','富友',NULL,NULL,NULL,'0',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10008','中金',NULL,NULL,NULL,'2',now(),'admin',now(),'admin');
INSERT INTO tz.t_tz_platform (id,"name",cert_no,bank,bank_code,platform_id,create_time,create_by,modify_time,modify_by) VALUES (
'10009','通联',NULL,NULL,NULL,'3',now(),'admin',now(),'admin');
delete from tz.t_tz_platform_province_limit;
INSERT INTO tz.t_tz_platform_province_limit (id,platform_limit,province,status,create_by,create_time,modify_by,modify_time) VALUES (
'8b4001c96e904d5eb6486206f84f2ab9','1','340000,120000,430000,440000,420000,320000,110000','0',NULL,NULL,NULL,NULL);
INSERT INTO tz.t_tz_platform_province_limit (id,platform_limit,province,status,create_by,create_time,modify_by,modify_time) VALUES (
'09d0754c4dcb4d00b94882462441a6ab','0','540000,520000,640000,410000,510000,530000,310000,340000,450000,370000,120000,430000,440000,350000,500000,220000,130000,360000,230000,330000,420000,210000,650000,610000,320000,460000,630000,140000,150000,620000,110000,710000,810000,820000','0',NULL,NULL,NULL,NULL);

delete from tz.t_tz_creditorper_rule_config;
INSERT INTO tz.t_tz_creditorper_rule_config (id,rule_name,remark,use_flag,create_by,create_time,modify_by,modify_time,default_flag,bill_type) VALUES (
'8b4001c96e904d5eb6486206f84f2ab9','首期','测试','1','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,'1');
INSERT INTO tz.t_tz_creditorper_rule_config (id,rule_name,remark,use_flag,create_by,create_time,modify_by,modify_time,default_flag,bill_type) VALUES (
'09d0754c4dcb4d00b94882462441a6ab','非首期','测试','1','admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,'2');
INSERT INTO tz.t_tz_creditorper_rule_config (id,rule_name,remark,use_flag,create_by,create_time,modify_by,modify_time,default_flag,bill_type) VALUES (
'83320ce6e8e4494f8c4195f4e58da91f','首期','','1','admin',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'),NULL,'1');
INSERT INTO tz.t_tz_creditorper_rule_config (id,rule_name,remark,use_flag,create_by,create_time,modify_by,modify_time,default_flag,bill_type) VALUES (
'd170f886390447008ccd86ed4fa2c0d5','非首期','','1','admin',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'),NULL,'2');

INSERT INTO tz.t_tz_creditorper_rule_proporti (id,rule_id,sort,proportion,use_flag,create_by,create_time,modify_by,modify_time) VALUES (
'873e6939f7524da782a004307b03273b','83320ce6e8e4494f8c4195f4e58da91f',1,70.00,'1','cf003',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'),'cf003',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_proporti (id,rule_id,sort,proportion,use_flag,create_by,create_time,modify_by,modify_time) VALUES (
'0ccd994501504eceb4279664419ad80c','83320ce6e8e4494f8c4195f4e58da91f',2,30.00,'1','cf003',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'),'cf003',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_proporti (id,rule_id,sort,proportion,use_flag,create_by,create_time,modify_by,modify_time) VALUES (
'5eb457e91e9440d3ad8a77dacc2e503a','d170f886390447008ccd86ed4fa2c0d5',1,60.00,'1','cf003',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'),'cf003',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_proporti (id,rule_id,sort,proportion,use_flag,create_by,create_time,modify_by,modify_time) VALUES (
'4fc9e01b64e24f4888eddba7155318f1','d170f886390447008ccd86ed4fa2c0d5',2,30.00,'1','cf003',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'),'cf003',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_proporti (id,rule_id,sort,proportion,use_flag,create_by,create_time,modify_by,modify_time) VALUES (
'00e86c86f60643ecb9f8e798bfd5507a','d170f886390447008ccd86ed4fa2c0d5',3,10.00,'1','cf003',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'),'cf003',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'));

INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'063c3dff2b664e8f9e982aa062c9ec39',1,'5b0d427add7c4e52acb60d72a0bd4275',100.00,'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'0dc2060522b244de9164d81b631ec097',1,'9699462e3b0845058ea862c28d1ed7c7',100.00,'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'3022cfa7507440899ae1762752519ac4',1,'7acb50487b764628b5b0d06e13a55c84',100.00,'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'809154e017b743b59ee052514acc1656',1,'0d99a5f45d7d4b35a67f8e7f31b4e749',80.00,'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'22f375a4a06a4552b0d6722ba12d1cda',2,'0d99a5f45d7d4b35a67f8e7f31b4e749',20.00,'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'1a3c40c989c84ebaaf7885fb7e0cb305',3,'0d99a5f45d7d4b35a67f8e7f31b4e749',100.00,'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'25cd67bc252943c0925c251134dca203',1,'3471fc6d22044b00a571780f350c1066',80.00,'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'128c7163d77248198ae4976d3c69a2ff',2,'3471fc6d22044b00a571780f350c1066',20.00,'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'1c68764398ff40059ce54f4d16ec8f05',3,'3471fc6d22044b00a571780f350c1066',100.00,'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'e473d3f8f28247acb92ea2a2f6d6bb08',1,'91a6fb1d138e4e92a267bb5098a1aa44',100.00,'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-13 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'ba9562a9206748a58874a5075ed88d00',1,'cf50b922e4c349f48ee794c14207ad92',20.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'caaf784b93c0411cadd1a422b2e71fb9',1,'7b6cb6684f644c529ab4d178d55582c7',10.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'705174cfba3941098281b8ab27aca0a0',1,'bb426b7b07c54bbf86e2fd78f326829d',100.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'1a5c0fc02a2247de9ee92bae70331a4d',1,'21dfb5fedbd74ae4a8e656dc049797ef',60.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'7a79d9e8386c4c27b055ff301095db5e',2,'21dfb5fedbd74ae4a8e656dc049797ef',40.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'238dcda67f684cf6b9a2f7104a5e9028',1,'d152fa8b729045df9737e70a74eefd97',30.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'8349592e00944a758cad5b2c80cf038e',2,'d152fa8b729045df9737e70a74eefd97',70.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'c824168ac4d84ffcb38ba8837e89a953',3,'d152fa8b729045df9737e70a74eefd97',30.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'e0751cf38be14d70bc3abde4535a3a1b',1,'d4830f90fc264788b99ef425c7c5076f',70.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'bdca28d034f64118905ba2568a76514e',2,'d4830f90fc264788b99ef425c7c5076f',30.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'ae9b4c8d5bdf4c4980b81d66be7ba29c',3,'d4830f90fc264788b99ef425c7c5076f',100.00,'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-19 00:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'a43acaad500e4c27b3dbf1152914ebd9',1,'873e6939f7524da782a004307b03273b',100.00,'admin',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'bd9ad70e24ed4d648dd6aa8a3d7a1487',1,'0ccd994501504eceb4279664419ad80c',100.00,'admin',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 11:03:50','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'1386c5f2438b4225b5fb02e6da46fcb6',1,'5eb457e91e9440d3ad8a77dacc2e503a',100.00,'admin',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'8313ed6c3d5d4fab929de57583035952',1,'4fc9e01b64e24f4888eddba7155318f1',100.00,'admin',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_tz_creditorper_rule_lower (id,sort,proportion_id,lower,create_by,create_time,modify_by,modify_time) VALUES (
'9d7839553b6744eba2f91ee3a90220e6',1,'00e86c86f60643ecb9f8e798bfd5507a',100.00,'admin',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-05-24 11:04:43','YYYY-MM-DD HH24:MI:SS'));

delete from tz.t_gl_sms_template;
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'3642a2e7e4794e1abf93e4c69ee54775',NULL,'5','非期限类到期提醒','【信和财富】尊敬的{#Name#}您好！您于{#custom_text_6#}出借的一笔{#custom_text_4#}{#custom_text_3#}于{#custom_text_1#}封闭期满，如需债权转让请联系您的客户经理，并于封闭期满前15天提交《出借人债权转让申请》，如继续出借，请忽略本信息！','1','到期日前30天',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'9c9884dacc094ec5850d229049b6cb31',NULL,'2','划扣成功','【信和财富】尊敬的{#Name#}您好，现确认您本期出借的{#custom_text_4#}元已划扣成功，感谢您的配合！祝您生活愉快！详询4000901199','1','数据管理部划扣成功
当天或下一工作日',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'4e5ce7e77bca4620a06812f8390f8e10',NULL,'1','划扣提醒','【信和财富】尊敬的{#Name#}:您本期出借的{#custom_text_4#}元即将划扣,请确保账户资金充足,感谢您配合！祝您生活愉快！详询4000901199','1','合同审核通过
划扣当天',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:35:30','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'a335d37114fb47ca91bfe11c4c6ee717',NULL,'3','划扣失败','【信和财富】尊敬的{#Name#}:您本期出借的{#custom_text_4#}元划扣失败，请确保{#custom_text_2#}银行尾号{#custom_text_5#}账户状态正常且资金充足并通知客户经理！详询4000901199','1','划扣失败当天或下一工作日',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:36:10','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'352f55068e004238953856c6998825bf',NULL,'14','部分划扣失败','【信和财富】尊敬的{#Name#}您好，本期出借的{#custom_text_4#}元部分划扣失败，请确保{#custom_text_2#}银行尾号{#custom_text_5#}账户状态正常且资金充足并通知客户经理！详询4000901199','1','划扣失败当天或下一工作日',NULL,'gt101',TO_DATE('2016-04-21 13:37:01','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:37:01','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'b41236b9026e491596a8df93719c0fc5',NULL,'4','期限类到期提醒','【信和财富】尊敬的{#Name#}您好!您于{#custom_text_6#}出借的{#custom_text_4#}{#custom_text_3#}于{#custom_text_1#}到期，请知悉！','1','到期日前10天',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:37:34','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'2afc83ed27ed41709c30bf70ac165222',NULL,'6','月息通收益回款提醒','【信和财富】尊敬的{#Name#}:您好!月息通本期收益{#custom_text_4#}元于今日进行回款,请注意查收！祝您生活愉快！详询4000901199','1','账单日当天或账单日下一工作日',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:39:23','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'b533950c731f447caa88feefbf611dae',NULL,'7','正常到期回款提醒','【信和财富】尊敬的{#Name#}您好!您于{#custom_text_6#}出借的{#custom_text_4#}元{#custom_text_3#}已到期，款项于今日进行回款,请注意查收！祝您生活愉快！详询4000901199','1','操作回款当天',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:39:50','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'77a039da75c549f2b495b064a1c5ddcb',NULL,'8','提前赎回回款提醒','【信和财富】尊敬的{#Name#}:您好!您的款项于今日进行回款,请注意查收！祝您生活愉快！详询4000901199','1','操作回款当天',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:53:37','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'73e22de185d842fc860612ce67b82ea0',NULL,'9','信和宝分次返收益提醒','【信和财富】尊敬的{#Name#}您好!您于{#custom_text_6#}出借的信和宝收益{#custom_text_4#}元于今日进行回款,请注意查收！祝您生活愉快！详询4000901199','1','满12个月，返还收益当天',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:53:55','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'f66439383b654457bd845f9c40f4c9b4',NULL,'16','信和月增返息提醒','【信和财富】尊敬的{#Name#}:您好!信和月增本期收益{#custom_text_4#}元于今日进行回款,请注意查收！祝您生活愉快！详询4000901199','1','操作回款当天',NULL,'gt101',TO_DATE('2016-04-21 13:55:35','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:55:53','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'249153c6ff2546ea8041593683a20ad3',NULL,'10','金账户开户短信','【信和财富】尊敬的{#Name#}:您账号为{#mobilephone#}的富友金账户已开户成功，可正常充值投资！祝您生活愉快！详询4000901199','1','金账户开户成功',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 13:56:17','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'6b1fcaa05c774053a5f6a745234f2eda',NULL,'13','内转出借','【信和财富】尊敬的{#Name#}:您于{#custom_text_6#}成功出借{#custom_text_4#}{#custom_text_3#}，感谢您的支持，祝您生活愉快！详询4000901199','1','再次出借当天',NULL,'gt101',TO_DATE('2016-04-21 14:02:19','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 14:02:19','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO tz.t_gl_sms_template (id,template_type,template_code,template_name,template_content,template_status,template_description,url,create_by,create_time,modify_by,modify_time) VALUES (
'c95a25c4c167484aad5a9bc34ee4ef20',NULL,'11','短信验证码','【信和财富】{#pin#}，此验证码用于信和财富预留手机号验证，请勿转发。祝您生活愉快！详询4000901199','1','短信验证码',NULL,'admin',TO_DATE('2016-03-23 19:02:05','YYYY-MM-DD HH24:MI:SS'),'gt101',TO_DATE('2016-04-21 14:03:06','YYYY-MM-DD HH24:MI:SS'));

delete from tz.t_tz_fy_area_code;
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北京市','1000','2','110',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1000');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天津市','120','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'120');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天津市','1100','2','120',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1100');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河北省','130','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'130');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'滦县','1243','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1243');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'滦南县','1244','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1244');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐亭县','1245','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1245');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'迁安市','1246','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1246');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'迁西县','1247','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1247');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'遵化市','1248','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1248');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉田县','1249','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1249');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'唐海县','1251','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1251');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'秦皇岛市','1260','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1260');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昌黎县','1262','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1262');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'抚宁县','1263','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1263');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'卢龙县','1264','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1264');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邯郸市','1270','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1270');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邯郸县','1271','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1271');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大名县','1281','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1281');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'魏县','1282','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1282');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曲周县','1283','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1283');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邱县','1284','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1284');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鸡泽县','1285','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1285');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肥乡县','1286','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1286');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广平县','1287','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1287');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'成安县','1288','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1288');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临漳县','1289','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1289');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'磁县','1291','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1291');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涉县','1292','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1292');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永年县','1293','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1293');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'馆陶县','1294','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1294');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武安市','1295','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1295');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邢台市','1310','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1310');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邢台县','1311','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1311');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南宫市','1321','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1321');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沙河市','1322','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1322');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临城县','1323','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1323');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柏乡县','1325','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1325');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'隆尧县','1326','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1326');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'任县','1327','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1327');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南和县','1328','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1328');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁晋县','1329','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1329');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巨鹿县','1331','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1331');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新河县','1332','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1332');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广宗县','1333','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1333');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平乡县','1334','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1334');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'威县','1335','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1335');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清河县','1336','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1336');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临西县','1337','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1337');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'保定市','1340','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1340');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'满城县','1341','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1341');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清苑县','1342','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1342');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定州市','1351','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1351');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涿州市','1352','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1352');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'易县','1353','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1353');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'徐水县','1354','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1354');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涞源县','1355','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1355');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定兴县','1356','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1356');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'顺平县','1357','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1357');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'唐县','1358','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1358');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'望都县','1359','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1359');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涞水县','1361','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1361');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高阳县','1362','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1362');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安新县','1363','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1363');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'雄县','1364','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1364');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'容城县','1365','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1365');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高碑店市','1366','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1366');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曲阳县','1367','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1367');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阜平县','1368','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1368');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安国市','1369','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1369');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'博野县','1371','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1371');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蠡县','1372','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1372');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'张家口市','1380','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1380');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宣化县','1381','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1381');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'张北县','1391','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1391');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'康保县','1392','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1392');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沽源县','1393','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1393');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'尚义县','1394','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1394');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蔚县','1395','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1395');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳原县','1396','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1396');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'怀安县','1397','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1397');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'万全县','1398','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1398');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'怀来县','1399','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1399');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涿鹿县','1401','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1401');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'赤城县','1402','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1402');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'崇礼县','1403','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1403');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'承德市','1410','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1410');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'承德县','1411','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1411');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宽城县','1421','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1421');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴隆县','1422','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1422');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平泉县','1423','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1423');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'滦平县','1424','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1424');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'隆化县','1426','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1426');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'围场县','1427','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1427');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沧州市','1430','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1430');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沧县','1431','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1431');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青县','1432','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1432');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泊头市','1441','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1441');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'任丘市','1442','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1442');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河间市','1443','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1443');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肃宁县','1444','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1444');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'献县','1445','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1445');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吴桥县','1446','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1446');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东光县','1447','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1447');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南皮县','1448','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1448');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盐山县','1449','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1449');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄骅市','1451','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1451');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海兴县','1453','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1453');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'廊坊市','1460','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1460');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三河市','1461','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1461');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'固安县','1462','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1462');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永清县','1463','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1463');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'香河县','1464','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1464');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大城县','1465','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1465');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'文安县','1466','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1466');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'霸州','1467','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1467');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'冀州','1482','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1482');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'枣强县','1483','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1483');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武邑县','1484','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1484');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'深州市','1485','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1485');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武强县','1486','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1486');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'饶阳县','1487','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1487');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安平县','1488','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1488');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'故城县','1489','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1489');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'景县','1491','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1491');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阜城县','1492','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1492');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石家庄市','1210','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1210');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'唐山市','1240','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1240');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'衡水市','1480','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1480');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青龙满族自治县','1261','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1261');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'内丘县','1324','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1324');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丰宁满族自治县','1425','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1425');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'孟村回族自治县','1452','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1452');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大厂回族自治县','1468','2','130',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1468');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'山西省','140','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'140');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴县','1735','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1735');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长治市','1640','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1640');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临县','1736','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1736');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳泉市','1630','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1630');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'右玉县','1638','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1638');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'怀仁县','1641','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1641');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岚县','1739','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1739');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大同市','1620','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1620');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳高县','1631','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1631');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天镇县','1632','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1632');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广灵县','1633','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1633');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浑源县','1635','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1635');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'左云县','1637','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1637');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大同县','1639','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1639');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和静县','8887','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8887');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平定县','1651','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1651');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盂县','1652','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1652');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潞城市','1662','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1662');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'襄垣县','1663','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1663');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'屯留县','1664','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1664');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平顺县','1665','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1665');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黎城县','1666','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1666');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'壶关县','1667','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1667');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长子县','1668','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1668');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武乡县','1669','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1669');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沁县','1671','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1671');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沁源县','1672','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1672');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'晋城市','1680','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1680');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沁水县','1681','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1681');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳城县','1682','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1682');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高平市','1683','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1683');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陵川县','1684','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1684');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'朔州市','1690','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1690');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'山阴县','1691','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1691');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'忻州市','1710','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1710');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定襄县','1712','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1712');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'五台县','1713','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1713');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'原平市','1714','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1714');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'代县','1715','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1715');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'繁峙县','1716','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1716');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁武县','1717','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1717');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'静乐县','1718','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1718');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'神池县','1719','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1719');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'五寨县','1721','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1721');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岢岚县','1722','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1722');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河曲县','1723','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1723');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'保德县','1724','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1724');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'偏关县','1725','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1725');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汾阳市','1731','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1731');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'文水县','1732','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1732');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'交城县','1733','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1733');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'孝义市','1734','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1734');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柳林县','1737','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1737');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石楼县','1738','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1738');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'方山县','1741','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1741');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'中阳县','1743','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1743');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'交口县','1744','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1744');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'晋中市','1750','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1750');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'榆社县','1752','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1752');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'左权县','1753','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1753');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和顺县','1754','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1754');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昔阳县','1755','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1755');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'寿阳县','1756','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1756');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'太谷县','1757','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1757');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'祁县','1758','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1758');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平遥县','1759','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1759');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'介休市','1761','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1761');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灵石县','1762','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1762');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临汾市','1770','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1770');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'侯马市','1772','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1772');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曲沃县','1773','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1773');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'翼城县','1774','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1774');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'襄汾县','1775','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1775');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洪洞县','1776','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1776');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'霍州市','1777','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1777');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'古县','1778','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1778');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安泽县','1779','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1779');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浮山县','1781','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1781');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉县','1782','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1782');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乡宁县','1783','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1783');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蒲县','1784','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1784');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大宁县','1785','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1785');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永和县','1786','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1786');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'隰县','1787','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1787');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汾西县','1788','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1788');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'运城市','1810','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1810');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永济市','1812','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1812');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'芮城县','1813','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1813');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临猗县','1814','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1814');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'万荣县','1815','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1815');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新绛县','1816','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1816');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'稷山县','1817','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1817');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河津市','1818','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1818');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'闻喜县','1819','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1819');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'夏县','1821','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1821');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绛县','1822','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1822');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平陆县','1823','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1823');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'垣曲县','1824','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1824');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'太原市','1610','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1610');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吕梁市','1730','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1730');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灵丘县','1634','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1634');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长治县','1661','2','140',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1661');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'内蒙古自治区','150','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'150');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'包头市','1920','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1920');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'土默特右旗','1921','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1921');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'固阳县','1922','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1922');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌海市','1930','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1930');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'赤峰市','1940','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1940');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿鲁科尔沁旗','1941','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1941');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴林左旗','1942','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1942');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴林右旗','1943','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1943');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'林西县','1944','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1944');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'克什克腾','1945','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1945');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'翁牛特旗','1946','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1946');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'喀喇沁旗','1947','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1947');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁城县','1948','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1948');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'敖汉旗','1949','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1949');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'呼伦贝尔市','1960','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1960');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'满州里市','1962','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1962');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扎兰屯市','1963','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1963');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'牙克石市','1964','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1964');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿荣旗','1965','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1965');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莫力达瓦达斡尔族旗','1966','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1966');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'额尔古纳市','1967','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1967');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'根河市','1968','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1968');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄂温克族自治旗','1971','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1971');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新巴尔虎右旗','1972','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1972');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新巴尔虎左旗','1973','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1973');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陈巴尔虎旗','1974','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1974');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴安盟','1980','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1980');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'科尔沁右翼中旗','1983','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1983');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扎赉特旗','1984','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1984');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'突泉县','1985','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1985');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿尔山市','1986','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1986');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通辽市','1990','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1990');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'霍林郭勒市','1992','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1992');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'科尔沁左翼中旗','1993','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1993');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'科尔沁左翼后旗','1994','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1994');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'开鲁县','1995','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1995');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'奈曼旗','1997','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1997');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扎鲁特旗','1998','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1998');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'锡林郭勒盟','2010','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2010');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'二连浩特市','2011','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2011');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿巴嘎旗','2013','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2013');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'苏尼特左旗','2014','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2014');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'苏尼特右旗','2015','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2015');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东乌珠穆沁旗','2016','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2016');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西乌珠穆沁旗','2017','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2017');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'太仆寺旗','2018','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2018');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镶黄旗','2019','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2019');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'正镶白旗','2021','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2021');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'正蓝旗','2022','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2022');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'多伦县','2023','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2023');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'卓资县','2035','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2035');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'化德县','2036','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2036');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'商都县','2037','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2037');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴和县','2038','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2038');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丰镇市','2039','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2039');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凉城县','2041','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2041');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'察哈尔右翼前旗','2042','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2042');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'察哈尔右翼中旗','2043','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2043');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'察哈尔右翼后旗','2044','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2044');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'四子王旗','2046','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2046');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄂尔多斯市','2050','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2050');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'达拉特旗','2052','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2052');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'准格尔旗','2053','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2053');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄂托克前旗','2054','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2054');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄂托克旗','2055','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2055');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'杭锦旗','2056','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2056');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌审旗','2057','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2057');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'五原县','2072','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2072');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'磴口县','2073','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2073');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌拉特前旗','2074','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2074');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌拉特中旗','2075','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2075');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌拉特后旗','2076','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2076');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'杭锦后旗','2077','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2077');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿拉善盟','2080','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2080');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'额济纳旗','2083','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2083');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'达尔罕茂明安联合旗','2045','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2045');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌兰察布市','2030','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2030');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴彦淖尔市','2070','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2070');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'呼和浩特市','1910','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1910');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄂伦春自治旗','1969','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1969');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'库伦旗','1996','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'1996');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'伊金霍洛旗','2058','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2058');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿拉善右旗','2082','2','150',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2082');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'辽宁省','210','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'210');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大连市','2220','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2220');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'普兰店市','2222','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2222');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'庄河市','2223','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2223');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'瓦房店市','2224','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2224');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鞍山市','2230','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2230');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'台安县','2231','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2231');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海城市','2232','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2232');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'抚顺市','2240','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2240');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'抚顺县','2241','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2241');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'本溪市','2250','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2250');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丹东市','2260','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2260');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤城市','2261','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2261');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东港市','2263','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2263');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'锦州市','2270','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2270');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凌海市','2272','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2272');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北宁市','2273','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2273');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黑山县','2274','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2274');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'义县','2275','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2275');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'营口市','2280','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2280');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盖州市','2282','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2282');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阜新市','2290','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2290');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'彰武县','2292','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2292');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'辽阳市','2310','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2310');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'辽阳县','2311','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2311');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灯塔市','2312','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2312');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盘锦市','2320','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2320');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大洼县','2321','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2321');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盘山县','2322','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2322');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铁岭市','2330','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2330');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西丰县','2332','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2332');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昌图县','2333','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2333');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'调兵山市','2336','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2336');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'开原市','2337','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2337');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'朝阳市','2340','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2340');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'建平县','2342','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2342');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凌源市','2343','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2343');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北票市','2346','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2346');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长海县','2225','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2225');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大石桥市','2262','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2262');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'葫芦岛市','2276','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2276');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥中县','2271','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2271');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'建昌县','2345','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2345');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴城市','2277','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2277');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岫岩县','2265','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2265');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沈阳市','2210','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2210');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新宾满族自治县','2242','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2242');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清原满族自治县','2243','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2243');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'本溪满族自治县','2251','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2251');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桓仁满族自治县','2252','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2252');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宽甸满族自治县','2264','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2264');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阜新蒙古族自治县','2291','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2291');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'喀喇沁左翼蒙古族自治县','2344','2','210',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2344');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉林省','220','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'220');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉林市','2420','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2420');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永吉县','2421','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2421');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临江市','2422','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2422');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'磐石市','2423','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2423');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蛟河市','2424','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2424');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桦甸市','2425','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2425');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'四平市','2430','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2430');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'梨树县','2431','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2431');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'双辽市','2433','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2433');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'公主岭市','2434','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2434');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'辽源市','2440','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2440');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东丰县','2441','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2441');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东辽县','2442','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2442');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通化市','2450','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2450');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通化县','2451','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2451');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'辉南县','2452','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2452');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柳河县','2453','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2453');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'梅河口市','2454','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2454');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'集安市','2455','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2455');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'白山市','2460','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2460');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'抚松县','2461','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2461');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'靖宇县','2462','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2462');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江源县','2464','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2464');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'白城市','2470','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2470');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洮南市','2472','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2472');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大安市','2474','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2474');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇赉县','2477','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2477');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通榆县','2478','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2478');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'图们市','2492','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2492');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'敦化市','2493','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2493');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'珲春市','2494','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2494');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙井市','2495','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2495');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和龙市','2496','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2496');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汪清县','2497','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2497');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安图县','2498','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2498');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'舒兰市','2426','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2426');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'松原市','2520','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2520');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扶余县','2473','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2473');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长岭县','2475','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2475');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乾安县','2479','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2479');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'前郭县','2476','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2476');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长春市','2410','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2410');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'延边朝鲜族自治州','2490','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2490');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'伊通满族自治县','2432','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2432');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长白朝鲜族自治县','2463','2','220',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2463');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黑龙江省','230','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'230');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'齐齐哈尔市','2640','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2640');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙江县','2641','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2641');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'讷河市','2642','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2642');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'依安县','2643','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2643');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泰来县','2644','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2644');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘南县','2645','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2645');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富裕县','2647','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2647');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'克山县','2649','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2649');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'克东县','2651','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2651');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'拜泉县','2652','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2652');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鸡西市','2660','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2660');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鸡东县','2661','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2661');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鹤岗市','2670','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2670');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'萝北县','2671','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2671');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥滨县','2672','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2672');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'双鸭山市','2680','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2680');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'集贤县','2681','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2681');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'佳木斯市','2690','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2690');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'伊春市','2710','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2710');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铁力市','2712','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2712');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'牡丹江市','2720','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2720');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桦南县','2721','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2721');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桦川县','2723','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2723');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汤原县','2725','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2725');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'抚远县','2727','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2727');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'同江市','2729','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2729');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富锦市','2731','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2731');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'七台河市','2740','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2740');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'勃利县','2741','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2741');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁安市','2751','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2751');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海林市','2752','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2752');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'穆棱市','2753','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2753');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东宁县','2754','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2754');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'林口县','2755','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2755');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥芬河市','2757','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2757');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥化市','2760','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2760');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安达市','2762','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2762');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肇东市','2763','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2763');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海伦市','2764','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2764');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'望奎县','2765','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2765');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兰西县','2766','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2766');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青冈县','2767','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2767');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'庆安县','2771','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2771');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥棱县','2773','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2773');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黑河市','2780','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2780');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北安市','2782','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2782');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'五大连池市','2783','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2783');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嫩江县','2784','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2784');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'逊克县','2786','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2786');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'孙吴县','2787','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2787');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'呼玛县','2791','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2791');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'塔河县','2792','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2792');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'漠河县','2793','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2793');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'虎林市','2756','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2756');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'密山市','2758','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2758');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宝清县','2724','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2724');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'饶河县','2726','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2726');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'友谊县','2728','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2728');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大庆市','2650','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2650');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'林甸县','2648','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2648');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肇源县','2768','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2768');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肇州县','2769','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2769');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'杜蒙县','2646','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2646');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'哈尔滨市','2610','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2610');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大兴安岭地区','2790','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2790');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嘉荫县','2711','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2711');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'明水县','2772','2','230',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2772');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上海市','310','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'310');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上海市','2900','2','310',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'2900');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江苏省','320','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'320');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南京','3010','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3010');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'无锡市','3020','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3020');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江阴市','3022','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3022');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜兴市','3023','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3023');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'徐州市','3030','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3030');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丰县','3031','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3031');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沛县','3032','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3032');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铜山县','3033','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3033');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'睢宁县','3034','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3034');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邳州市','3035','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3035');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新沂市','3036','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3036');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'常州市','3040','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3040');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金坛市','3042','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3042');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'溧阳市','3043','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3043');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'苏州市','3050','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3050');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'太仓市','3051','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3051');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昆山市','3052','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3052');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吴江市','3054','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3054');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'常熟市','3055','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3055');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'张家港市','3056','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3056');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南通市','3060','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3060');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海安县','3061','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3061');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'如皋市','3062','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3062');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'如东县','3063','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3063');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海门市','3065','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3065');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'启东市','3066','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3066');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'连云港市','3070','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3070');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'赣榆县','3071','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3071');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东海县','3072','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3072');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灌云县','3073','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3073');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淮安市','3080','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3080');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涟水县','3085','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3085');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洪泽县','3087','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3087');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盱眙县','3088','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3088');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金湖县','3089','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3089');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盐城市','3110','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3110');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'响水县','3111','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3111');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'滨海县','3112','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3112');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阜宁县','3113','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3113');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'射阳县','3114','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3114');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'建湖县','3115','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3115');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大丰市','3116','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3116');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东台市','3117','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3117');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扬州市','3120','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3120');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高邮市','3121','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3121');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宝应县','3122','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3122');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江都市','3125','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3125');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仪征市','3129','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3129');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇江市','3140','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3140');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'勾容市','3142','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3142');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扬中市','3143','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3143');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通州市','3084','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3084');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灌南县','3082','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3082');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丹阳市','3141','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3141');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泰州市','3128','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3128');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴化市','3131','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3131');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'靖江市','3123','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3123');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泰兴市','3124','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3124');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'姜堰市','3132','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3132');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宿迁市','3090','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3090');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沭阳县','3083','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3083');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泗阳县','3093','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3093');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泗洪县','3086','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3086');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宿豫县','3094','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3094');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盐都县','3118','2','320',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3118');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浙江省','330','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'330');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁波市','3320','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3320');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'象山县','3321','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3321');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁海县','3322','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3322');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'余姚市','3324','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3324');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'慈溪市','3325','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3325');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'奉化市','3326','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3326');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'温州市','3330','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3330');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洞头县','3332','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3332');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐清市','3333','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3333');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永嘉县','3334','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3334');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平阳县','3335','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3335');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'苍南县','3336','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3336');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'文成县','3337','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3337');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泰顺县','3338','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3338');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'瑞安市','3339','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3339');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嘉兴市','3350','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3350');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嘉善县','3351','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3351');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平湖市','3352','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3352');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海盐县','3353','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3353');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桐乡市','3354','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3354');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海宁市','3355','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3355');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湖州市','3360','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3360');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德清县','3361','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3361');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长兴县','3362','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3362');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安吉县','3363','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3363');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绍兴市','3370','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3370');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绍兴县','3371','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3371');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上虞市','3372','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3372');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嵊州市','3373','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3373');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新昌县','3374','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3374');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'诸暨市','3375','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3375');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金华市','3380','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3380');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永康市','3382','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3382');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武义县','3383','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3383');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浦江县','3384','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3384');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'磐安县','3385','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3385');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兰溪市','3386','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3386');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'义乌市','3387','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3387');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东阳市','3388','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3388');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'衢州市','3410','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3410');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'常山县','3412','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3412');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'开化县','3413','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3413');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙游县','3414','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3414');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江山市','3415','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3415');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岱山县','3421','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3421');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嵊泗县','3422','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3422');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丽水市','3430','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3430');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'云和县','3433','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3433');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'庆元县','3434','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3434');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'缙云县','3435','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3435');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'遂昌县','3436','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3436');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'松阳县','3437','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3437');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙泉市','3439','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3439');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'台州市','3450','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3450');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临海市','3452','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3452');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'温岭市','3454','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3454');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仙居县','3455','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3455');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天台县','3456','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3456');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三门县','3457','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3457');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉环县','3458','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3458');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'舟山市','3420','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3420');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'杭州市','3310','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3310');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青田县','3432','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3432');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'景宁畲族自治县','3438','2','330',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3438');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安徽省','340','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'340');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宣城市','3771','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3771');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巢湖市','3781','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3781');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涡阳县','3726','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3726');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蒙城县','3727','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3727');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'利辛县','3732','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3732');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'毫州市','3722','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3722');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'芜湖市','3620','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3620');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'芜湖县','3621','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3621');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'繁昌县','3622','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3622');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南陵县','3623','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3623');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蚌埠市','3630','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3630');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'怀远县','3631','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3631');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'五河县','3632','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3632');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'固镇县','3633','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3633');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淮南市','3640','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3640');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤台县','3641','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3641');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'马鞍山市','3650','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3650');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'当涂县','3651','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3651');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淮北市','3660','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3660');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'濉溪县','3661','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3661');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铜陵市','3670','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3670');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铜陵县','3671','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3671');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安庆市','3680','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3680');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桐城市','3681','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3681');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'怀宁县','3682','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3682');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'枞阳县','3683','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3683');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潜山县','3684','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3684');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'太湖县','3685','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3685');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宿松县','3686','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3686');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'望江县','3687','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3687');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岳西县','3688','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3688');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄山市','3710','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3710');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'歙县','3711','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3711');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'休宁县','3712','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3712');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黟县','3713','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3713');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'祁门县','3714','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3714');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临泉县','3724','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3724');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'太和县','3725','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3725');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阜南县','3728','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3728');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'颍上县','3729','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3729');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'界首市','3731','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3731');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宿州市','3740','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3740');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'砀山县','3742','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3742');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'萧县','3743','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3743');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灵璧县','3745','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3745');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泗县','3746','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3746');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'滁州市','3750','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3750');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天长市','3752','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3752');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'来安县','3753','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3753');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'全椒县','3754','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3754');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定远县','3755','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3755');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤阳县','3756','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3756');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'明光市','3757','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3757');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'六安市','3760','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3760');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'寿县','3763','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3763');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'霍邱县','3764','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3764');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'舒城县','3765','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3765');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金寨县','3766','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3766');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'霍山县','3767','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3767');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郎溪县','3772','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3772');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广德县','3773','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3773');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁国市','3774','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3774');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泾县','3775','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3775');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'旌德县','3776','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3776');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绩溪县','3777','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3777');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'庐江县','3782','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3782');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'无为县','3783','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3783');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'含山县','3784','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3784');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和县','3785','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3785');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'池州市','3790','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3790');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东至县','3792','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3792');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石台县','3793','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3793');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青阳县','3794','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3794');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'合肥市','3610','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3610');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阜阳市','3720','2','340',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3720');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'福建省','350','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'350');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永安市','3960','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3960');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武夷山市','4022','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4022');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莆田县','3941','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3941');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'厦门市','3930','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3930');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莆田市','3940','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3940');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仙游县','3942','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3942');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三明市','3950','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3950');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'明溪县','3951','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3951');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清流县','3952','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3952');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁化县','3953','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3953');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大田县','3954','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3954');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'尤溪县','3955','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3955');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沙县','3956','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3956');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'将乐县','3957','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3957');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泰宁县','3958','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3958');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'建宁县','3959','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3959');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泉州市','3970','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3970');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'惠安县','3971','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3971');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'晋江市','3972','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3972');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南安市','3973','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3973');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安溪县','3974','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3974');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永春县','3975','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3975');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德化县','3976','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3976');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石狮市','3978','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3978');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'漳州市','3990','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3990');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙海市','3991','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3991');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'云霄县','3992','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3992');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'漳浦县','3993','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3993');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'诏安县','3994','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3994');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长泰县','3995','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3995');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东山县','3996','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3996');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南靖县','3997','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3997');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平和县','3998','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3998');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华安县','3999','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3999');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南平市','4010','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4010');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邵武市','4012','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4012');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'顺昌县','4013','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4013');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'建阳市','4014','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4014');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'建瓯市','4015','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4015');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浦城县','4016','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4016');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'光泽县','4018','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4018');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'松溪县','4019','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4019');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'政和县','4021','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4021');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁德市','4030','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4030');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'福鼎市','4032','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4032');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'霞浦县','4033','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4033');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'福安市','4034','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4034');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'古田县','4035','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4035');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'屏南县','4036','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4036');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'寿宁县','4037','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4037');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'周宁县','4038','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4038');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柘荣县','4039','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4039');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙岩市','4050','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4050');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长汀县','4052','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4052');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永定县','4053','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4053');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上杭县','4054','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4054');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武平县','4055','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4055');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'漳平市','4056','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4056');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'连城县','4057','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4057');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'福州市','3910','2','350',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'3910');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江西省','360','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'360');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莲花县','4364','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4364');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南昌','4210','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4210');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'景德镇市','4220','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4220');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐平市','4221','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4221');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浮梁县','4222','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4222');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'萍乡市','4230','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4230');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上栗县','4231','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4231');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'芦溪县','4232','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4232');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'九江市','4240','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4240');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'九江县','4241','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4241');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'瑞昌市','4242','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4242');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武宁县','4243','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4243');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'修水县','4244','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4244');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永修县','4245','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4245');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德安县','4246','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4246');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'星子县','4247','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4247');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'都昌县','4248','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4248');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湖口县','4249','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4249');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'彭泽县','4251','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4251');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新余市','4260','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4260');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'分宜县','4261','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4261');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鹰潭市','4270','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4270');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贵溪市','4271','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4271');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'余江县','4272','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4272');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'赣州市','4280','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4280');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'赣县','4282','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4282');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南康市','4283','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4283');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'信丰县','4284','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4284');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大余县','4285','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4285');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上犹县','4286','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4286');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'崇义县','4287','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4287');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安远县','4288','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4288');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙南县','4289','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4289');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定南县','4291','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4291');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'全南县','4292','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4292');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁都县','4293','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4293');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'于都县','4294','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4294');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴国县','4295','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4295');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'瑞金市','4296','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4296');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'会昌县','4297','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4297');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'寻乌县','4298','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4298');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石城县','4299','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4299');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜春市','4310','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4310');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丰城市','4312','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4312');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'樟树市','4313','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4313');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高安市','4314','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4314');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'奉新县','4315','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4315');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'万载县','4316','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4316');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上高县','4317','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4317');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜丰县','4318','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4318');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'靖安县','4319','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4319');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铜鼓县','4321','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4321');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上饶市','4330','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4330');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上饶县','4332','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4332');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广丰县','4333','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4333');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉山县','4334','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4334');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铅山县','4335','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4335');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'横峰县','4336','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4336');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'弋阳县','4337','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4337');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'余干县','4338','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4338');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'波阳县','4339','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4339');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'万年县','4341','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4341');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德兴市','4342','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4342');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'婺源县','4343','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4343');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉安市','4350','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4350');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'井冈山市','4352','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4352');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉安县','4353','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4353');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉水县','4354','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4354');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'峡江县','4355','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4355');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新干县','4356','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4356');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永丰县','4357','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4357');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泰和县','4358','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4358');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'遂川县','4359','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4359');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'万安县','4361','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4361');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安福县','4362','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4362');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永新县','4363','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4363');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'抚州市','4370','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4370');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南城县','4372','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4372');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黎川县','4373','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4373');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南丰县','4374','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4374');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'崇仁县','4375','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4375');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐安县','4376','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4376');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜黄县','4377','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4377');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金溪县','4378','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4378');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'资溪县','4379','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4379');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东乡县','4381','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4381');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广昌县','4382','2','360',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4382');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'山东省','370','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'370');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高青县','4535','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4535');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沂源县','4536','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4536');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平邑县','4741','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4741');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青岛市','4520','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4520');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'即墨市','4521','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4521');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'胶南市','4522','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4522');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莱西市','4523','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4523');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平度市','4524','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4524');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'胶州市','4525','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4525');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淄博市','4530','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4530');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桓台县','4531','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4531');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'枣庄市','4540','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4540');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'滕州市','4541','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4541');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东营市','4550','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4550');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'垦利县','4551','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4551');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'利津县','4552','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4552');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广饶县','4553','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4553');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'烟台市','4560','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4560');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蓬莱市','4561','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4561');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'招远市','4562','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4562');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'栖霞市','4563','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4563');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海阳市','4564','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4564');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长岛县','4566','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4566');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙口市','4567','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4567');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莱阳市','4568','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4568');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莱州市','4569','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4569');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潍坊市','4580','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4580');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安丘市','4581','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4581');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'寿光市','4582','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4582');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临朐县','4583','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4583');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昌乐县','4584','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4584');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'梁山县','4757','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4757');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'日照市','4732','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4732');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'五莲县','4587','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4587');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莒县','4736','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4736');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莱芜市','4634','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4634');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昌邑市','4585','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4585');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高密市','4586','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4586');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青州市','4588','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4588');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'诸城市','4589','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4589');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'济宁市','4610','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4610');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兖州市','4611','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4611');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邹城市','4612','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4612');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'微山县','4613','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4613');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鱼台县','4614','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4614');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金乡县','4615','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4615');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嘉祥县','4616','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4616');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汶上县','4617','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4617');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泗水县','4618','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4618');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曲阜市','4619','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4619');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泰安市','4630','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4630');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁阳县','4631','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4631');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肥城市','4632','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4632');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东平县','4633','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4633');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新泰市','4635','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4635');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'威海市','4650','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4650');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乳山市','4651','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4651');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'文登市','4652','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4652');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'荣成市','4653','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4653');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'滨州市','4660','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4660');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'惠民县','4662','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4662');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳信县','4663','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4663');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'无棣县','4664','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4664');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沾化县','4665','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4665');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'博兴县','4666','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4666');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邹平县','4667','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4667');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德州市','4680','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4680');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐陵市','4682','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4682');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陵县','4683','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4683');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平原县','4684','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4684');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'夏津县','4685','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4685');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武城县','4686','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4686');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'齐河县','4687','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4687');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'禹城市','4688','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4688');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临邑县','4689','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4689');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁津县','4693','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4693');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'庆云县','4694','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4694');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'聊城市','4710','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4710');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临清市','4712','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4712');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳谷县','4713','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4713');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莘县','4714','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4714');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'茌平县','4715','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4715');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东阿县','4716','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4716');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'冠县','4717','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4717');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高唐县','4718','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4718');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临沂市','4730','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4730');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郯城县','4733','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4733');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'苍山县','4734','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4734');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莒南县','4735','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4735');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沂水县','4737','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4737');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蒙阴县','4739','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4739');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'费县','4742','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4742');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沂南县','4743','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4743');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临沭县','4744','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4744');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曹县','4752','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4752');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定陶县','4753','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4753');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'成武县','4754','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4754');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'单县','4755','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4755');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巨野县','4756','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4756');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郓城县','4758','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4758');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄄城县','4759','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4759');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东明县','4761','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4761');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'济南市','4510','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4510');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'菏泽市','4750','2','370',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4750');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河南省','410','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'410');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郾城县','5043','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5043');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'信阳县','5154','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5154');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'舞钢市','5044','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5044');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'襄城县','4955','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4955');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'开封市','4920','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4920');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'杞县','4921','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4921');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通许县','4922','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4922');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'尉氏县','4923','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4923');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'开封县','4924','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4924');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兰考县','4925','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4925');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洛阳市','4930','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4930');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'偃师市','4931','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4931');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'孟津县','4932','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4932');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新安县','4933','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4933');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嵩县','4935','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4935');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汝阳县','4936','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4936');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜阳县','4937','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4937');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洛宁县','4938','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4938');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'伊川县','4939','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4939');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平顶山市','4950','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4950');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'叶县','4952','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4952');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鲁山县','4953','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4953');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郏县','4954','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4954');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汝州市','4956','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4956');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安阳市','4960','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4960');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'林州市','4961','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4961');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安阳县','4962','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4962');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汤阴县','4963','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4963');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'滑县','4964','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4964');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'内黄县','4965','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4965');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鹤壁市','4970','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4970');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浚县','4971','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4971');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淇县','4972','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4972');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新乡市','4980','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4980');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新乡县','4981','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4981');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'获嘉县','4982','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4982');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'原阳县','4983','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4983');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'延津县','4984','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4984');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'封丘县','4985','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4985');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长垣县','4986','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4986');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'卫辉市','4987','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4987');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'辉县市','4988','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4988');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'焦作市','5010','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5010');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'修武县','5011','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5011');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'博爱县','5012','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5012');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武陟县','5013','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5013');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沁阳市','5014','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5014');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'温县','5015','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5015');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'孟州市','5016','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5016');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'濮阳市','5020','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5020');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清丰县','5021','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5021');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南乐县','5022','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5022');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'范县','5023','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5023');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'台前县','5024','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5024');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'濮阳县','5025','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5025');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'许昌市','5030','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5030');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长葛市','5031','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5031');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'许昌县','5032','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5032');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄢陵县','5033','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5033');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'禹州市','5034','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5034');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'漯河市','5040','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5040');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'舞阳县','5041','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5041');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三门峡市','5050','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5050');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'渑池县','5051','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5051');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陕县','5052','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5052');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灵宝市','5053','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5053');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'卢氏县','5054','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5054');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'义马市','5055','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5055');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'商丘市','5060','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5060');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'虞城县','5062','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5062');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'民权县','5064','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5064');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁陵县','5065','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5065');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'睢县','5066','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5066');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'夏邑县','5067','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5067');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柘城县','5068','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5068');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永城市','5069','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5069');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'周口市','5080','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5080');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扶沟县','5082','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5082');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西华县','5083','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5083');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'商水县','5084','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5084');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'太康县','5085','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5085');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鹿邑县','5086','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5086');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郸城县','5087','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5087');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淮阳县','5088','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5088');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沈丘县','5089','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5089');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'项城市','5091','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5091');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'驻马店市','5110','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5110');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'确山县','5112','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5112');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泌阳县','5113','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5113');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'遂平县','5114','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5114');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西平县','5115','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5115');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上蔡县','5116','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5116');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汝南县','5117','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5117');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平舆县','5118','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5118');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新蔡县','5119','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5119');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'正阳县','5121','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5121');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南阳市','5130','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5130');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邓州市','5132','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5132');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南召县','5133','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5133');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'方城县','5134','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5134');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西峡县','5135','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5135');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇平县','5137','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5137');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'内乡县','5138','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5138');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淅川县','5139','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5139');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'社旗县','5141','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5141');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'唐河县','5142','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5142');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新野县','5143','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5143');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桐柏县','5144','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5144');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'信阳市','5150','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5150');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'息县','5152','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5152');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淮滨县','5153','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5153');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潢川县','5155','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5155');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'光山县','5156','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5156');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'固始县','5157','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5157');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'商城县','5158','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5158');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'罗山县','5159','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5159');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新县','5161','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5161');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郑州市','4910','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4910');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'栾川县','4934','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4934');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宝丰县','4951','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'4951');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临颍县','5042','2','410',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5042');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'京山县','5382','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5382');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沙洋县','5383','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5383');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'钟祥市','5381','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5381');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜昌县','5263','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5263');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'随州市','5286','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5286');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广水市','5354','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5354');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'赤壁市','5362','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5362');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'枣阳市','5288','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5288');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳新县','5367','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5367');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郧县','5392','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5392');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郧西县','5393','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5393');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'竹山县','5394','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5394');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'竹溪县','5395','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5395');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'房县','5396','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5396');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丹江口市','5391','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5391');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜昌市','5260','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5260');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜都市','5261','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5261');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武汉市','5210','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5210');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄石市','5220','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5220');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大冶市','5221','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5221');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'十堰市','5230','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5230');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'当阳市','5262','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5262');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'枝江市','5264','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5264');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'远安县','5265','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5265');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴山县','5266','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5266');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'秭归县','5267','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5267');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜城县','5282','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5282');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南漳县','5283','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5283');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'谷城县','5284','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5284');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'保康县','5285','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5285');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'老河口市','5287','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5287');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄂州市','5310','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5310');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'神农架林区','5311','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5311');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'荆门市','5320','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5320');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄冈市','5330','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5330');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'麻城市','5331','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5331');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武穴县','5332','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5332');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'团风县','5333','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5333');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'红安县','5334','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5334');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'罗田县','5335','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5335');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'英山县','5336','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5336');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浠水县','5337','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5337');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蕲春县','5338','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5338');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄梅县','5339','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5339');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'孝感市','5350','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5350');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'连山壮族瑶族自治县','6015','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6015');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'连南瑶族自治县','6016','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6016');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广西壮族自治区','450','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'450');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柳州市','6140','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6140');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柳江县','6141','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6141');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柳城县','6142','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6142');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鹿寨县','6152','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6152');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'融安县','6156','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6156');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桂林市','6170','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6170');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳朔县','6171','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6171');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临桂县','6172','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6172');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灵川县','6181','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6181');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴安县','6183','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6183');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永福县','6184','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6184');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灌阳县','6185','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6185');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'资源县','6187','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6187');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平乐县','6188','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6188');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'梧州市','6210','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6210');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'苍梧县','6211','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6211');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岑溪市','6221','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6221');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'藤县','6222','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6222');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蒙山县','6224','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6224');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北海市','6230','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6230');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'合浦县','6231','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6231');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉林市','6240','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6240');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'容县','6245','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6245');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北流市','6246','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6246');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陆川县','6247','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6247');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'博白县','6248','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6248');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴业县','6249','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6249');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'田阳县','6262','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6262');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'田东县','6263','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6263');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平果县','6264','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6264');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'靖西县','6266','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6266');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'那坡县','6267','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6267');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凌云县','6268','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6268');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐业县','6269','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6269');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'田林县','6271','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6271');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西林县','6273','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6273');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜州市','6282','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6282');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南丹县','6285','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6285');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天峨县','6286','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6286');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤山县','6287','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6287');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东兰县','6288','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6288');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灵山县','6314','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6314');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浦北县','6315','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6315');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'防城港市','6330','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6330');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'象州市','6153','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6153');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金秀县','6159','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6159');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'百色市','6261','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6261');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德保县','6275','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6275');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河池市','6281','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6281');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'钦州市','6311','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6311');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'上思县','6312','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6312');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贵港市','6242','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6242');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平南县','6244','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6244');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桂平市','6243','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6243');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贺州市','6225','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6225');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昭平县','6223','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6223');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'钟山县','6226','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6226');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'来宾市','6155','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6155');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'忻城县','6161','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6161');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武宣县','6154','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6154');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'合山市','6151','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6151');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'崇左市','6128','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6128');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扶绥县','6127','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6127');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁明县','6132','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6132');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙州县','6133','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6133');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大新县','6129','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6129');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天等县','6131','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6131');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凭祥市','6121','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6121');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南宁市','6110','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6110');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三江侗族自治县','6157','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6157');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'融水苗族自治县','6158','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6158');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'全州县','6182','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6182');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙胜各族自治县','6186','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6186');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'荔蒲县','6189','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6189');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'恭城瑶族自治县','6191','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6191');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'隆林各族自治县','6272','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6272');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'罗城仫佬族自治县','6283','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6283');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'环江毛南族自治县','6284','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6284');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴马瑶族自治县','6289','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6289');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'都安瑶族自治县','6291','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6291');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大化瑶族自治县','6292','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6292');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平南县','6331','2','450',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6331');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海南省','460','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'460');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海口','6410','2','460',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6410');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三亚','6420','2','460',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6420');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'重庆市','500','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'500');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'城口县','6681','2','500',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6681');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武隆县','6695','2','500',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6695');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'云阳县','6676','2','500',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6676');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'奉节县','6677','2','500',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6677');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巫山县','6678','2','500',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6678');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巫溪县','6679','2','500',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6679');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'重庆市','6530','2','500',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6530');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'四川省','510','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'510');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'自贡市','6550','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6550');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'荣县','6551','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6551');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富顺县','6552','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6552');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'攀枝花市','6560','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6560');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'米易县','6561','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6561');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盐边县','6562','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6562');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泸州市','6570','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6570');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泸县','6571','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6571');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'合江县','6572','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6572');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'叙永县','6574','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6574');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'古蔺县','6575','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6575');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绵竹县','6581','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6581');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'中江县','6582','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6582');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'什邡县','6583','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6583');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广汉市','6584','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6584');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'罗江县','6585','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6585');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绵阳市','6590','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6590');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三台县','6591','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6591');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盐亭县','6592','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6592');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安县','6593','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6593');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'梓潼县','6594','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6594');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平武县','6596','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6596');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江油市','6597','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6597');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广元市','6610','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6610');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'旺苍县','6611','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6611');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青川县','6612','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6612');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'剑阁县','6613','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6613');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'苍溪县','6614','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6614');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蓬溪县','6621','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6621');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'射洪县','6622','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6622');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大英县','6623','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6623');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'内江市','6630','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6630');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'威远县','6634','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6634');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'资中县','6635','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6635');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'隆昌县','6638','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6638');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐山市','6650','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6650');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'犍为县','6653','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6653');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'井研县','6654','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6654');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'夹江县','6655','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6655');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沐川县','6658','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6658');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'峨眉山市','6664','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6664');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜宾市','6710','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6710');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜宾县','6712','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6712');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南溪县','6713','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6713');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江安县','6714','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6714');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长宁县','6715','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6715');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高县','6716','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6716');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'筠连县','6717','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6717');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'珙县','6718','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6718');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴文县','6719','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6719');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'屏山县','6721','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6721');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南充市','6730','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6730');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南部县','6734','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6734');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'营山县','6736','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6736');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蓬安县','6738','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6738');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仪陇县','6739','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6739');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西充县','6742','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6742');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阆中县','6743','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6743');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'达州市','6750','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6750');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'达县','6752','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6752');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宣汉县','6753','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6753');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'开江县','6754','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6754');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'万源县','6755','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6755');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大竹县','6761','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6761');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'渠县','6762','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6762');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'雅安市','6770','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6770');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'名山县','6772','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6772');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'荥经县','6773','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6773');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汉源县','6774','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6774');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石棉县','6775','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6775');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天全县','6776','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6776');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'芦山县','6777','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6777');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宝兴县','6778','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6778');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汶川县','6791','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6791');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'理县','6792','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6792');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'茂县','6793','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6793');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'松潘县','6794','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6794');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'九寨沟县','6795','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6795');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'小金县','6797','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6797');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'壤塘县','6801','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6801');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿坝县','6802','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6802');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'若尔盖县','6803','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6803');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'红原县','6804','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6804');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泸定县','6812','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6812');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丹巴县','6813','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6813');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'九龙县','6814','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6814');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'雅江县','6815','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6815');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'道孚县','6816','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6816');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'炉霍县','6817','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6817');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘孜县','6818','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6818');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新龙县','6819','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6819');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德格县','6821','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6821');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'白玉县','6822','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6822');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石渠县','6823','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6823');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'色达县','6824','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6824');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'理塘县','6825','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6825');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴塘县','6826','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6826');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乡城县','6827','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6827');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'稻城县','6828','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6828');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'得荣县','6829','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6829');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盐源县','6843','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6843');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'会理县','6845','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6845');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'会东县','6846','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6846');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁南县','6847','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6847');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'普格县','6848','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6848');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'布拖县','6849','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6849');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金阳县','6851','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6851');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昭觉县','6852','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6852');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'喜德县','6853','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6853');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'冕宁县','6854','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6854');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'越西县','6855','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6855');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘洛县','6856','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6856');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'美姑县','6857','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6857');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'雷波县','6858','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6858');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'万州区','6670','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6670');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涪陵区','6690','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6690');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丰都县','6694','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6694');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黔江区','6870','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6870');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富山县','6227','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6227');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'纳溪县','6573','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6573');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'资阳县','6636','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6636');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'酉阳县','6874','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6874');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'彭水县','6875','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6875');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'哈密地区','8840','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8840');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昌吉回族自治州','8850','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8850');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'博尔塔拉蒙古自治州','8870','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8870');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿克陶县','8932','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8932');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'木垒哈萨克自治县','8858','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8858');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金川县','6805','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6805');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德昌县','6859','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6859');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'眉山市','6652','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6652');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仁寿县','6651','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6651');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'彭山县','6657','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6657');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洪雅县','6656','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6656');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丹棱县','6665','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6665');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青神县','6659','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6659');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广安市','6737','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6737');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岳池县','6735','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6735');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武胜县','6741','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6741');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邻水县','6763','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6763');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华蓥市','6732','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6732');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴中市','6758','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6758');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通江县','6756','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6756');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南江县','6757','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6757');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平昌县','6759','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6759');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安岳县','6633','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6633');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐至县','6632','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6632');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'简阳市','6637','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6637');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'成都市','6510','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6510');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德阳市','6580','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6580');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'遂宁市','6620','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6620');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿坝藏族羌族自治州','6790','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6790');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘孜藏族自治州','6810','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6810');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凉山彝族自治州','6840','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6840');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北川羌族自治县','6595','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6595');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'峨边彝族自治县','6662','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6662');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'马边彝族自治县','6663','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6663');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黑水县','6798','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6798');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'木里藏族自治县','6842','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6842');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洪雅县','6673','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6673');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丹棱县','6674','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6674');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青神县','6675','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6675');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武胜县','6692','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6692');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邻水县','6693','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6693');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通江县','6871','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6871');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南江县','6872','2','510',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6872');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贵州省','520','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'520');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'六盘水市','7020','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7020');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盘县','7022','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7022');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'遵义市','7030','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7030');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'遵义县','7032','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7032');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桐梓县','7033','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7033');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥阳县','7034','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7034');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'正安县','7035','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7035');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'道真县','7036','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7036');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'务川县','7037','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7037');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湄潭县','7039','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7039');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'余庆县','7041','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7041');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仁怀市','7042','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7042');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'赤水市','7043','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7043');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'习水县','7044','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7044');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江口县','7052','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7052');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石阡县','7054','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7054');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'思南县','7055','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7055');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'印江县','7056','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7056');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德江县','7057','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7057');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'万山','7061','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7061');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴仁县','7072','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7072');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'普安县','7073','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7073');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'晴隆县','7074','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7074');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贞丰县','7075','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7075');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'册亨县','7077','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7077');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安龙县','7078','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7078');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大方县','7092','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7092');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黔西县','7093','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7093');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金沙县','7094','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7094');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'织金县','7095','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7095');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'纳雍县','7096','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7096');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'威宁县','7097','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7097');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'赫章县','7098','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7098');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安顺市','7110','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7110');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平坝县','7117','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7117');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'普定县','7118','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7118');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'关岭县','7119','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7119');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇宁县','7121','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7121');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'紫云县','7122','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7122');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄平县','7132','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7132');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'施秉县','7133','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7133');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三穗县','7134','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7134');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇远县','7135','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7135');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岑巩县','7136','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7136');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天柱县','7137','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7137');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'锦屏县','7138','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7138');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'剑河县','7139','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7139');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'台江县','7141','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7141');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黎平县','7142','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7142');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'榕江县','7143','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7143');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'从江县','7144','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7144');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'雷山县','7145','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7145');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'麻江县','7146','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7146');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丹寨县','7147','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7147');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'荔波县','7152','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7152');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贵定县','7153','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7153');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'福泉县','7154','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7154');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'独山县','7156','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7156');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平塘县','7157','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7157');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'罗甸县','7158','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7158');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长顺县','7159','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7159');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙里县','7161','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7161');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'惠水县','7162','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7162');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贵阳市','7010','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7010');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铜仁地区','7050','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7050');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黔西南布依族苗族自治州','7070','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7070');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'毕节地区','7090','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7090');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黔东南苗族侗族自治州','7130','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7130');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黔南布依族苗族自治州','7150','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7150');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'水城县','7021','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7021');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤冈县','7038','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7038');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉屏侗族自治县','7053','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7053');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沿河土家族自治县','7058','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7058');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'松桃苗族自治县','7059','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7059');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'望谟县','7076','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7076');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'瓮安县','7155','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7155');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三都水族自治县','7163','2','520',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7163');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'云南省','530','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'530');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'梁河县','7543','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7543');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昭通市','7340','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7340');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鲁甸县','7342','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7342');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巧家县','7343','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7343');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盐津县','7344','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7344');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大关县','7345','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7345');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永善县','7346','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7346');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥江县','7347','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7347');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇雄县','7348','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7348');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'彝良县','7349','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7349');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'威信县','7351','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7351');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永富县','7352','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7352');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曲靖市','7360','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7360');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'马龙县','7362','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7362');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宣威市','7363','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7363');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富源县','7364','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7364');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'罗平县','7365','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7365');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'师宗县','7366','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7366');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陆良县','7367','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7367');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'会泽县','7369','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7369');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沾益县','7371','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7371');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'双柏县','7382','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7382');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'牟定县','7383','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7383');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南华县','7384','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7384');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'姚安县','7385','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7385');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大姚县','7386','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7386');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永仁县','7387','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7387');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'元谋县','7388','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7388');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武定县','7389','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7389');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'禄丰县','7391','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7391');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉溪市','7410','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7410');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江川县','7412','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7412');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'澄江县','7413','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7413');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通海县','7414','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7414');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华宁县','7415','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7415');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'易门县','7416','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7416');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'峨山县','7417','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7417');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新平县','7418','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7418');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'元江县','7419','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7419');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'开远市','7432','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7432');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蒙自县','7433','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7433');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'建水县','7435','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7435');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石屏县','7436','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7436');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'弥勒县','7437','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7437');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泸西县','7438','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7438');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'元阳县','7439','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7439');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'红河县','7441','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7441');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绿春县','7443','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7443');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河口县','7444','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7444');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'砚山县','7452','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7452');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西畴县','7453','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7453');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'麻栗坡县','7454','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7454');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'马关县','7455','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7455');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广南县','7457','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7457');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富宁县','7458','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7458');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'景谷县','7475','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7475');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'勐海县','7492','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7492');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'勐腊县','7493','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7493');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'祥云县','7513','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7513');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宾川县','7514','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7514');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'弥渡县','7515','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7515');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永平县','7518','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7518');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'云龙县','7519','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7519');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洱源县','7521','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7521');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'剑川县','7522','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7522');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鹤庆县','7523','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7523');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'保山市','7530','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7530');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'施甸县','7532','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7532');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'腾冲县','7533','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7533');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙陵县','7534','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7534');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昌宁县','7535','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7535');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盈江县','7544','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7544');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陇川县','7545','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7545');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'瑞丽市','7546','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7546');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永胜县','7552','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7552');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华坪县','7553','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7553');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'福贡县','7562','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7562');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德钦县','7572','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7572');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤庆县','7582','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7582');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永德县','7584','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7584');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇康县','7585','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7585');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'耿马县','7587','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7587');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昆明市','7310','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7310');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'楚雄彝族自治州','7380','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7380');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'红河哈尼族彝族自治州','7430','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7430');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'文山壮族苗族自治州','7450','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7450');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'普洱市','7470','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7470');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西双版纳傣族自治州','7490','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7490');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大理白族自治州','7510','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7510');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德宏傣族景颇族自治州','7540','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7540');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丽江市','7550','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7550');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'怒江傈僳族自治州','7560','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7560');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'迪庆藏族自治州','7570','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7570');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临沧市','7580','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7580');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'屏边苗族自治县','7434','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7434');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金平苗族瑶族傣族自治县','7442','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7442');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丘北县','7456','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7456');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁洱哈尼族彝族自治县','7472','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7472');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'墨江哈尼族自治县','7473','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7473');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'景东彝族自治县','7474','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7474');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇沅彝族哈尼族拉祜族自治县','7476','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7476');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江城哈尼族彝族自治县','7477','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7477');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'孟连傣族拉祜族佤族自治县','7478','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7478');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'澜沧拉祜族自治县','7479','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7479');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西盟佤族自治县','7481','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7481');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'漾濞彝族自治县','7512','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7512');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南涧彝族自治县','7516','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7516');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巍山彝族回族自治县','7517','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7517');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁蒗彝族自治县','7554','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7554');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贡山独龙族怒族自治县','7563','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7563');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兰坪白族普米族自治县','7564','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7564');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'维西傈僳族自治县','7573','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7573');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'云县','7583','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7583');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'双江拉祜族佤族布朗族傣族自治县','7586','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7586');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沧源佤族自治县','7588','2','530',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7588');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西藏自治区','540','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'540');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'边坝县','7730','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7730');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'错那县','7750','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7750');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'康马县','7770','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7770');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'樟木口岸','7711','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7711');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江达县','7721','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7721');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贡觉县','7722','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7722');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'类乌齐县','7723','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7723');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丁青县','7724','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7724');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'察雅县','7725','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7725');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'八宿县','7726','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7726');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'左贡县','7727','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7727');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'芒康县','7728','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7728');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洛隆县','7729','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7729');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扎囊县','7741','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7741');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贡嘎县','7742','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7742');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桑日县','7743','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7743');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'琼结县','7744','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7744');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曲松县','7745','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7745');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'措美县','7746','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7746');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洛扎县','7747','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7747');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'加查县','7748','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7748');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'隆子县','7749','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7749');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'浪卡子县','7751','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7751');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南木林县','7761','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7761');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江孜县','7762','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7762');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定日县','7763','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7763');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'萨迦县','7764','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7764');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'拉孜县','7765','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7765');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昂仁县','7766','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7766');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'谢通门县','7767','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7767');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'白朗县','7768','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7768');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仁布县','7769','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7769');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定结县','7771','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7771');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仲巴县','7772','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7772');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'亚东县','7773','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7773');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉隆县','7774','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7774');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'聂拉木县','7775','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7775');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'萨嘎县','7776','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7776');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岗巴县','7777','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7777');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嘉黎县','7791','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7791');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'比如县','7792','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7792');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'聂荣县','7793','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7793');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安多县','7794','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7794');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'申扎县','7795','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7795');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'索县','7796','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7796');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'班戈县','7797','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7797');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴青县','7798','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7798');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'尼玛县','7799','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7799');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'普兰县','7811','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7811');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'札达县','7812','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7812');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'日土县','7813','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7813');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'革吉县','7814','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7814');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'改则县','7815','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7815');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'措勤县','7816','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7816');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'工布江达县','7831','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7831');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'米林县','7832','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7832');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'墨脱县','7833','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7833');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'波密县','7834','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7834');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'察隅县','7835','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7835');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'朗县','7836','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7836');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'拉萨市','7700','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7700');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昌都地区','7720','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7720');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'山南地区','7740','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7740');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'日喀则地区','7760','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7760');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'那曲地区','7790','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7790');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿里地区','7810','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7810');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'林芝地区','7830','2','540',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7830');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陕西省','610','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'610');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'双湖','7800','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7800');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'耀县','7921','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7921');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'铜川市','7920','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7920');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜君县','7922','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7922');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤翔县','7932','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7932');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岐山县','7933','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7933');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'扶风县','7934','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7934');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'眉县','7935','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7935');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陇县','7936','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7936');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'千阳县','7937','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7937');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'麟游县','7938','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7938');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤县','7939','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7939');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'太白县','7941','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7941');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'咸阳市','7950','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7950');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴平市','7951','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7951');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三原县','7952','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7952');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泾阳县','7953','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7953');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乾县','7954','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7954');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'礼泉县','7955','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7955');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永寿县','7956','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7956');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'彬县','7957','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7957');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长武县','7958','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7958');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'旬邑县','7959','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7959');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'淳化县','7961','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7961');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武功县','7962','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7962');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'渭南市','7970','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7970');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'韩城市','7972','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7972');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华县','7973','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7973');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华阴市','7974','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7974');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潼关县','7975','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7975');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大荔县','7976','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7976');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蒲城县','7977','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7977');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'澄城县','7978','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7978');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'白水县','7979','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7979');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'合阳县','7981','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7981');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富平县','7982','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7982');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汉中市','7990','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7990');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南郑县','7992','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7992');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'城固县','7993','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7993');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洋县','7994','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7994');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西乡县','7995','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7995');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'勉县','7996','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7996');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁强县','7997','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7997');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'略阳县','7998','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7998');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇巴县','7999','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7999');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'留坝县','8001','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8001');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'佛坪县','8002','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8002');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安康市','8010','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8010');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汉阴县','8012','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8012');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石泉县','8013','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8013');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁陕县','8014','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8014');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'紫阳县','8015','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8015');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岚皋县','8016','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8016');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平利县','8017','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8017');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇坪县','8018','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8018');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'旬阳县','8019','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8019');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'白河县','8021','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8021');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'商洛市','8030','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8030');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洛南县','8032','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8032');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丹凤县','8033','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8033');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'商南县','8034','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8034');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'山阳县','8035','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8035');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇安县','8036','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8036');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柞水县','8037','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8037');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'延安市','8040','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8040');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'延长县','8042','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8042');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'延川县','8043','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8043');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安塞县','8045','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8045');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'志丹县','8046','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8046');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘泉县','8048','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8048');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富县','8049','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8049');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洛川县','8051','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8051');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜川县','8052','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8052');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄龙县','8053','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8053');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄陵县','8054','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8054');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'榆林市','8060','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8060');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'神木县','8062','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8062');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'府谷县','8063','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8063');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'横山县','8064','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8064');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'靖边县','8065','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8065');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定边县','8066','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8066');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥德县','8067','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8067');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'米脂县','8068','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8068');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'佳县','8069','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8069');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吴堡县','8071','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8071');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清涧县','8072','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8072');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'子洲县','8073','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8073');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西安市','7910','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7910');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宝鸡市','7930','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'7930');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吴起县','8047','2','610',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8047');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘肃省','620','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'620');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平川','8244','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8244');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武都县','8311','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8311');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嘉峪关市','8220','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8220');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金昌市','8230','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8230');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永昌县','8231','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8231');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'白银市','8240','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8240');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'靖远县','8241','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8241');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'会宁县','8242','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8242');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'景泰县','8243','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8243');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清水县','8251','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8251');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'秦安县','8252','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8252');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘谷县','8253','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8253');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武山县','8254','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8254');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'酒泉市','8260','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8260');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉门市','8261','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8261');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'敦煌市','8263','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8263');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'金塔县','8264','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8264');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿克塞县','8266','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8266');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'张掖市','8270','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8270');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'民乐县','8273','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8273');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临泽县','8274','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8274');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高台县','8275','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8275');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'山丹县','8276','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8276');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武威市','8280','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8280');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'民勤县','8282','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8282');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'古浪县','8283','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8283');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'定西市','8290','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8290');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通渭县','8292','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8292');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陇西县','8293','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8293');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'渭源县','8294','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8294');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'漳县','8296','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8296');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岷县','8297','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8297');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宕昌县','8312','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8312');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'成县','8313','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8313');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'康县','8314','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8314');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'文县','8315','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8315');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西和县','8316','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8316');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'礼县','8317','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8317');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'两当县','8318','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8318');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'徽县','8319','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8319');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平凉市','8330','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8330');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泾川县','8332','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8332');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'灵台县','8333','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8333');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'崇信县','8334','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8334');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华亭县','8335','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8335');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'庄浪县','8336','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8336');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'静宁县','8337','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8337');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'庆阳市','8340','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8340');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'庆城县','8342','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8342');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'环县','8343','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8343');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华池县','8344','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8344');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'合水县','8345','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8345');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'正宁县','8346','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8346');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁县','8347','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8347');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'镇原县','8348','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8348');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临夏县','8362','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8362');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'康乐县','8363','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8363');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永靖县','8364','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8364');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广河县','8365','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8365');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和政县','8366','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8366');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'积石山县','8368','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8368');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临潭县','8381','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8381');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'卓尼县','8382','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8382');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'舟曲县','8383','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8383');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'迭部县','8384','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8384');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玛曲县','8385','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8385');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'碌曲县','8386','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8386');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'夏河县','8387','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8387');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兰州市','8210','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8210');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天水市','8250','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8250');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陇南市','8310','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8310');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临夏回族自治州','8360','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8360');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘南藏族自治州','8380','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8380');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'张家川回族自治县','8255','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8255');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肃北蒙古族自治县','8265','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8265');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'瓜州县','8267','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8267');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肃南裕固族自治县','8272','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8272');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天祝藏族自治县','8284','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8284');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临洮县','8295','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8295');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东乡族自治县','8367','2','620',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8367');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青海省','630','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'630');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海东地区','8520','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8520');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平安县','8521','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8521');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'民和回族土族自治县','8522','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8522');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐都县','8523','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8523');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'互助土族自治县','8526','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8526');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'化隆回族自治县','8527','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8527');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'循化撒拉族自治县','8528','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8528');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'门源回族自治县','8541','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8541');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'祁连县','8542','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8542');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海晏县','8543','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8543');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'刚察县','8544','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8544');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'同仁县','8551','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8551');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'尖扎县','8552','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8552');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泽库县','8553','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8553');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'共和县','8561','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8561');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'同德县','8562','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8562');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贵德县','8563','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8563');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴海县','8564','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8564');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'贵南县','8565','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8565');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玛沁县','8571','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8571');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'班玛县','8572','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8572');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'甘德县','8573','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8573');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'达日县','8574','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8574');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'久治县','8575','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8575');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玛多县','8576','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8576');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉树县','8581','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8581');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'杂多县','8582','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8582');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'称多县','8583','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8583');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'治多县','8584','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8584');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'囊谦县','8585','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8585');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曲麻莱县','8586','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8586');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'格尔木市','8591','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8591');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德令哈市','8592','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8592');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌兰县','8593','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8593');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'都兰县','8594','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8594');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天峻县','8595','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8595');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西宁市','8510','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8510');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海北藏族自治州','8540','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8540');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'黄南藏族自治州','8550','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8550');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海南藏族自治州','8560','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8560');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'果洛藏族自治州','8570','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8570');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玉树藏族自治州','8580','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8580');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海西蒙古族藏族自治州','8590','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8590');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河南蒙古族自治县','8554','2','630',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8554');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁夏回族自治区','640','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'640');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陶乐县','8722','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8722');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'惠农县','8723','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8723');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'中卫县','8733','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8733');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吴忠市','8731','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8731');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'固原市','8741','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8741');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'中宁县','8734','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8734');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海原县','8742','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8742');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石嘴山市','8720','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8720');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平罗县','8721','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8721');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青铜峡市','8732','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8732');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'盐池县','8736','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8736');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'同心县','8737','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8737');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'西吉县','8743','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8743');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'隆德县','8744','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8744');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泾源县','8745','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8745');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'彭阳县','8746','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8746');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'银川市','8710','2','640',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8710');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新疆维吾尔自治区','650','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'650');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'木垒哈萨克自治县','8859','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8859');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北屯县','8844','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8844');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新疆博州阿拉山口口岸','8847','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8847');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石河子市','9028','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9028');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'三道岭县','9029','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9029');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿拉尔市','9031','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9031');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'图木舒克市','9032','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9032');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'精河县','8872','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8872');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'温泉县','8873','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8873');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'轮台县','8882','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8882');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'尉犁县','8883','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8883');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'若羌县','8884','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8884');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'且末县','8885','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8885');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'焉耆回族自治县','8886','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8886');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和硕县','8888','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8888');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'博湖县','8889','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8889');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'温宿县','8912','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8912');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'库车县','8913','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8913');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沙雅县','8914','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8914');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新和县','8915','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8915');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'拜城县','8916','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8916');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌什县','8917','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8917');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿瓦提县','8918','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8918');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'柯坪县','8919','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8919');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿合奇县','8933','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8933');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌恰县','8934','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8934');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'疏附县','8942','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8942');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'疏勒县','8943','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8943');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'英吉沙县','8944','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8944');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泽普县','8945','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8945');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'莎车县','8946','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8946');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'叶城县','8947','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8947');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'子长县','8044','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8044');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'麦盖提县','8948','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8948');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岳普湖县','8949','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8949');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'伽师县','8951','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8951');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴楚县','8952','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8952');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'塔什库尔干塔吉克县','8953','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8953');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'墨玉县','8963','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8963');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'皮山县','8964','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8964');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洛浦县','8965','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8965');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'策勒县','8966','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8966');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'于田县','8967','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8967');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'民丰县','8968','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8968');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'奎屯市','8981','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8981');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'伊宁县','8992','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8992');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'察布查尔锡伯自治县','8993','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8993');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'霍城县','8994','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8994');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巩留县','8995','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8995');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新源县','8996','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8996');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'昭苏县','8997','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8997');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'特克斯县','8998','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8998');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'尼勒克县','8999','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8999');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'额敏县','9012','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9012');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌苏市','9013','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9013');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沙湾县','9014','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9014');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'托里县','9015','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9015');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'裕民县','9016','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9016');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和布克赛尔蒙古自治县','9017','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9017');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'布尔津县','9022','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9022');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'富蕴县','9023','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9023');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'福海县','9024','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9024');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'哈巴河县','9025','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9025');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'青河县','9026','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9026');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉木乃县','9027','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9027');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乌鲁木齐','8810','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8810');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'克拉玛依市','8820','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8820');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鄯善县','8832','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8832');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'托克逊县','8833','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8833');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴里坤哈萨克自治县','8842','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8842');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'伊吾县','8843','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8843');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'米泉市','8852','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8852');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'呼图壁县','8853','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8853');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'玛纳斯县','8854','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8854');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'奇台县','8855','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8855');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阜康市','8856','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8856');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吉木萨尔县','8857','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8857');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴音郭楞蒙古自治州','8880','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8880');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿克苏地区','8910','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8910');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'克孜勒苏柯尔克孜自治州','8930','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8930');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'喀什地区','8940','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8940');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和田地区','8960','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8960');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'伊犁哈萨克自治州','8980','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8980');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'塔城地区','9010','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9010');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阿勒泰地区','9020','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'9020');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吐鲁番地区','8830','2','650',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'8830');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'孝昌县','5351','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5351');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'应城市','5352','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5352');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安陆市','5353','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5353');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大悟县','5355','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5355');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'云梦县','5356','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5356');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汉川县','5357','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5357');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'咸宁市','5360','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5360');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嘉鱼县','5363','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5363');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通城县','5364','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5364');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'崇阳县','5365','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5365');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通山县','5366','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5366');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'荆州市','5370','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5370');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仙桃市','5371','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5371');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石首市','5372','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5372');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洪湖市','5373','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5373');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'天门市','5374','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5374');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潜江市','5375','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5375');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'松滋市','5377','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5377');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'公安县','5378','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5378');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'监利县','5379','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5379');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'利川市','5412','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5412');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'巴东县','5414','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5414');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宣恩县','5415','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5415');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'咸丰县','5416','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5416');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'来凤县','5417','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5417');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鹤峰县','5418','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5418');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'襄阳市','5280','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5280');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'恩施土家族苗族自治州','5410','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5410');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长阳土家族自治县','5268','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5268');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'五峰土家族自治县','5269','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5269');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'建始县','5413','2','420',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5413');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湖南省','430','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'430');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'道县','5654','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5654');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宁远县','5655','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5655');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江永县','5656','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5656');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蓝山县','5658','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5658');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新田县','5659','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5659');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'双牌县','5661','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5661');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'祁阳县','5662','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5662');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'怀化市','5670','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5670');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洪江市','5672','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5672');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沅陵县','5674','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5674');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'辰溪县','5675','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5675');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'溆浦县','5676','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5676');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'会同县','5681','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5681');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'靖州县','5682','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5682');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'泸溪县','5692','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5692');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'凤凰县','5693','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5693');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'花垣县','5694','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5694');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'保靖县','5695','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5695');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'古丈县','5696','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5696');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永顺县','5697','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5697');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙山县','5698','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5698');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'双峰县','5624','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5624');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新化县','5625','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5625');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郴州市','5630','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5630');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'资兴市','5632','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5632');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桂阳县','5634','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5634');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永兴县','5635','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5635');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'宜章县','5636','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5636');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'嘉禾县','5637','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5637');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临武县','5638','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5638');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汝城县','5639','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5639');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桂东县','5641','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5641');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安仁县','5642','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5642');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'永州市','5650','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5650');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东安县','5653','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5653');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'炎陵县','5526','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5526');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'长沙','5510','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5510');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'株洲县','5521','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5521');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'茶陵县','5523','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5523');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'澧县','5583','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5583');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'醴陵市','5525','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5525');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湘潭市','5530','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5530');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湘乡市','5532','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5532');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'韶山县','5533','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5533');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'衡阳市','5540','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5540');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'衡阳县','5541','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5541');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'衡南县','5542','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5542');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'衡山县','5543','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5543');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'衡东县','5544','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5544');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'常宁县','5545','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5545');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'祁东县','5546','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5546');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'耒阳县','5547','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5547');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邵阳市','5550','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5550');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邵东县','5551','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5551');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新邵县','5552','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5552');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'邵阳县','5553','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5553');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'隆回县','5554','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5554');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'洞口县','5555','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5555');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'武冈县','5556','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5556');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'绥宁县','5557','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5557');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新宁县','5558','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5558');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岳阳市','5570','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5570');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'岳阳县','5571','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5571');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临湘县','5572','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5572');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'华容县','5573','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5573');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湘阴县','5574','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5574');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平江县','5575','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5575');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汩罗市','5576','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5576');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'常德市','5580','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5580');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安乡县','5581','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5581');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汉寿县','5582','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5582');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'临澧县','5584','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5584');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桃源县','5585','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5585');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'石门县','5586','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5586');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'津市市','5587','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5587');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'张家界市','5590','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5590');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'慈利县','5591','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5591');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桑植县','5592','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5592');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'益阳市','5610','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5610');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'沅江县','5612','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5612');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南县','5614','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5614');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'桃江县','5615','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5615');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'安化县','5616','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5616');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'娄底市','5620','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5620');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'冷水江市','5622','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5622');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'涟源县','5623','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5623');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湘西土家族苗族自治州','5690','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5690');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'株洲市','5520','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5520');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江华瑶族自治县','5657','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5657');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'麻阳苗族自治县','5677','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5677');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新晃侗族自治县','5678','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5678');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'芷江侗族自治县','5679','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5679');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'通道侗族自治县','5683','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5683');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'攸县','5522','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5522');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'城步苗族自治县','5559','2','430',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5559');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广东省','440','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'440');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广州','5810','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5810');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'韶关市','5820','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5820');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'始兴县','5822','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5822');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南雄市','5823','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5823');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'仁化县','5824','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5824');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乐昌市','5825','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5825');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'翁源县','5826','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5826');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新丰县','5828','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5828');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'深圳','5840','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5840');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'珠海市','5850','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5850');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汕头市','5860','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5860');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'南澳县','5863','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5863');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'佛山市','5880','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5880');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'江门市','5890','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5890');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'台山市','5892','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5892');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'鹤山市','5895','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5895');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湛江市','5910','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5910');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'吴川市','5911','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5911');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'廉江市','5912','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5912');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'雷州市','5914','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5914');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'徐闻县','5915','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5915');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'茂名市','5920','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5920');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'信宜市','5921','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5921');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高州市','5922','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5922');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'电白县','5923','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5923');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'化州市','5924','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5924');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'肇庆市','5930','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5930');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'高要市','5931','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5931');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'四会市','5932','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5932');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'广宁县','5933','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5933');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'怀集县','5934','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5934');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'封开县','5935','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5935');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'德庆县','5936','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5936');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'惠州市','5950','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5950');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'博罗县','5952','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5952');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'惠东县','5953','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5953');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙门县','5954','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5954');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'梅州市','5960','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5960');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大埔县','5962','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5962');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'丰顺县','5963','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5963');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'五华县','5964','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5964');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'兴宁市','5965','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5965');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'平远县','5966','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5966');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'蕉岭县','5967','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5967');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'汕尾市','5970','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5970');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'海丰县','5971','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5971');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陆丰市','5972','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5972');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'陆河县','5973','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5973');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'河源市','5980','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5980');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'紫金县','5981','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5981');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'龙川县','5982','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5982');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'连平县','5983','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5983');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'和平县','5984','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5984');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东源县','5985','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5985');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳江市','5990','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5990');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳西县','5991','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5991');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳春市','5992','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5992');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳东县','5993','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5993');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清远市','6010','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6010');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'佛冈县','6011','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6011');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'英德市','6012','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6012');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'阳山县','6013','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6013');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'连州市','6014','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6014');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'清新县','6017','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6017');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'东莞市','6020','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6020');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'中山市','6030','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'6030');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'曲江县','5821','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5821');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'澄海市','5861','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5861');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潮阳市','5864','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5864');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潮安营业部','5872','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5872');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'大亚湾营业部','5956','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5956');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'潮州市','5869','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5869');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'饶平县','5862','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5862');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'揭阳市','5865','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5865');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'揭东县','5871','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5871');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'揭西县','5866','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5866');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'惠来县','5868','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5868');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'普宁市','5867','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5867');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'云浮市','5937','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5937');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'新兴县','5938','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5938');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'郁南县','5939','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5939');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'罗定市','5941','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5941');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'乳源瑶族自治县','5827','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5827');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'恩平市','5893','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5893');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'开平市','5894','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5894');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'遂溪县','5913','2','440',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'5913');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'湖北省','420','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'420');
INSERT INTO tz.t_tz_fy_area_code (area_name,area_code,area_type,parent_id,create_by,create_time,modify_by,modify_time,id) VALUES (
'北京市','110','1','0',NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),NULL,TO_DATE('2016-03-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'110');

delete from tz.t_tz_fy_bank;
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0000','电子联行转换中心',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0001','中国人民银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0002','中国邮政储蓄银行有限责任公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0011','国家金库',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0012','支付业务收费专户',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0201','国家开发银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0202','中国进出口银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0203','中国农业发展银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0302','中信实业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0307','平安银行股份有限公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0313','其他城市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0314','其他农村商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0315','恒丰银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0316','浙商银行股份有限公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0317','其他农村合作银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0318','渤海银行股份有限公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0319','徽商银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0320','村镇银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0321','重庆三峡银行股份有限公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0322','上海农村商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0401','城市信用社',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0402','其他农村信用合作社',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0404','烟台市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0409','济南市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0410','深圳平安银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0412','温州市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0422','石家庄市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0423','杭州市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0424','南京市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0431','临沂市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0450','青岛市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0458','西宁市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0461','长沙市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0462','潍坊市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0481','威海商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0497','莱芜市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0501','汇丰银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0502','东亚银行(中国)有限公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0503','南洋商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0504','恒生银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0505','中国银行（香港）',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0506','集友银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0507','创兴银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0509','星展银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0510','永亨银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0515','德州市商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0531','花旗银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0532','美国银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0533','摩根大通银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0534','美国建东银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0561','三菱东京日联银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0562','日本日联银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0563','三井住友银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0564','瑞穗实业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0565','日本山口银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0567','外资银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0591','外换银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0592','韩国新韩银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0593','友利银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0594','韩国产业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0595','新韩银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0596','企业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0597','韩亚银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0616','首都银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0621','华侨银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0622','大华银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0623','星展银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0631','盘谷银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0641','奥地利奥合国际银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0651','比利时联合银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0652','比利时富通银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0661','荷兰银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0662','荷兰安智银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0671','香港地区渣打银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0672','英国苏格兰皇家银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0680','永隆银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0681','瑞典商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0682','瑞典北欧斯安银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0691','法国兴业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0692','法国巴黎银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0694','东方汇理银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0695','法国外贸银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0711','德国德累斯登银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0712','德意志银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0713','德国商业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0714','德国西德银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0715','德国巴伐利亚州银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0716','德国北德意志州银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0717','中德住房储蓄银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0732','意大利联合圣保罗银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0741','瑞士信贷银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0742','瑞士银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0751','加拿大丰业银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0752','蒙特利尔银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0761','澳大利亚和新西兰银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0771','摩根士丹利国际银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0775','华美银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0776','荷兰合作银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0781','厦门国际银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0782','法国巴黎银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0786','韩亚银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0787','华一银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0901','中央结算公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0902','公开市场业务操作室',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0903','中国外汇交易中心',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0904','城市商业银行资金清算中心',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0905','中国银联股份有限公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0906','各地清算中心',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0907','财务公司',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0908','电子商业汇票系统处理中心',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0909','银行间市场清算所',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0969','澳门地区中国银行澳门分行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'1401','邯郸市城市信用社',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'1408','顺德市农村信用合作社联合社',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'1420','宁波鄞州农村合作银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'1434','临汾市尧都区农村信用合作社联合社',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'A000','境外银行',NULL);
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0102','中国工商银行','102');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0103','中国农业银行','103');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0104','中国银行','104');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0105','中国建设银行','105');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0514','中信银行','302');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0304','华夏银行','304');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0305','中国民生银行','305');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0783','平安银行','307');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0308','招商银行','308');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0309','兴业银行','309');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0310','上海浦东发展银行','310');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0301','交通银行','666');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0303','中国光大银行','303');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0306','广东发展银行','306');
INSERT INTO tz.t_tz_fy_bank (bank_code,fy_bank,sys_bank_code) VALUES (
'0403','国家邮政局邮政储汇局','403');
INSERT INTO tz."t_tz_platform_province_limit"(id, platform_limit, province, status) VALUES(
'1111111111111','2','540000,520000,640000,410000,510000,530000,310000,340000,450000,370000,120000,430000,440000,350000,500000,220000,130000,360000,230000,330000,420000,210000,650000,610000,320000,460000,630000,140000,150000,620000,110000,710000,810000,820000','0');
INSERT INTO tz."t_tz_platform_province_limit"(id, platform_limit, province, status) VALUES(
'222222222','1','340000,120000,430000,440000,420000,320000,110000','0');
DELETE FROM tz.t_tz_product_convert_coefficient;
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'83120101','83','0.25','2012-01-01','2015-07-21','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'90120101','90','1.5','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'89120101','89','1','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'88120101','88','1','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'85120101','85','1','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'84120101','84','0.5','2012-01-01','2015-07-21','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'87120101','87','1.45','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'91120101','91','1.45','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'92120101','92','1.5','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'93120101','93','1.4','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'81120101','81','1.1','2012-01-01','2014-11-30','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'86120101','86','0.08','2012-01-01','2015-11-30','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'82120101','82','1','2012-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'81141201','81','1','2014-12-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'83150722','83','0.3','2015-07-22','2015-08-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'84150722','84','0.55','2015-07-22','2015-08-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'83150901','83','0.25','2015-09-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'84150901','84','0.5','2015-09-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'86151201','86','0.1','2015-12-01','2015-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'86160101','86','0.08','2016-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'66160101','66','1.5','2016-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'67160101','67','1.5','2016-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());
INSERT INTO tz.t_tz_product_convert_coefficient (id,product_code,convert_coefficient,start_date,end_date,create_by,create_time,modify_by,modify_time) VALUES (
'80120101','80','1','2016-01-01','2099-12-31','rptadmin',now(),'rptadmin',now());

INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'736a82317c6a436893837bb0eacc6454','RegularlySendMailService_fortune','RegularlySendMailService','fortune','非首期债权文件邮件发送','com.creditharmony.fortunejob.facade.RegularlySendMailService','regularlySendMail','0 0 5 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'493d49e28f304b1fb62601e59af753f5','WealthProductReminderService_fortune','WealthProductReminderService','fortune','财富产品到期提醒列表更新及待发送提醒短信生成','com.creditharmony.fortunejob.facade.WealthProductReminderService','wealthProductReminder','0 0 0 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'9dde8c38d0064c7eabe52c2340f3268a','CreditorStatisticsManager_fortune','CreditorStatisticsManager','fortune','债权分配每日统计','com.creditharmony.fortunejob.fortune.statistics.service.CreditorStatisticsManager','excute','0 0 23 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'6d2fe3009e0e452a84b77762e46cd430','SysncDocumentService_fortune','SysncDocumentService','fortune','财富档案对接','com.creditharmony.fortunejob.facade.SysncDocumentService','sysncDocument','0 0 20 ? * THU','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'15a9e5637a2a404fb41bd3b92c328cf9','CreditorAvailableDayService_fortune','CreditorAvailableDayService','fortune','债权可用天数批处理','com.creditharmony.fortunejob.fortune.CreditorAvailableDayService','run','0 30 23 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'dfd1e6fc89e342c3b6e7c65174a93d87','CustomerBirthdayReminderService_fortune','CustomerBirthdayReminderService','fortune','客户生日提醒列表更新及待发送生日短信生成','com.creditharmony.fortunejob.facade.CustomerBirthdayReminderService','customerBirthdayReminder','0 0 1 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'cbbf352a33294454857c8c71d4976d5b','EmailInfoService_fortune','EmailInfoService','fortune','实时批量发送首期债权邮件','com.creditharmony.fortunejob.facade.EmailInfoService','actualTimeSendEmail','0 0/5 * * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'8633728359094d40a4225807db92d996','FortuneChangeProductTypeService_fortune','FortuneChangeProductTypeService','fortune','财富产品类型状态变更','com.creditharmony.fortunejob.facade.FortuneChangeProductTypeService','changeProductType','0 0 12 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'f3d87b41f36e4502b16672c3880f6b13','TripleEmployeeService_fortune','TripleEmployeeService','fortune','三网用户信息同步','com.creditharmony.fortunejob.facade.TripleEmployeeService','tripleEmployee','20 0/2 * * * ?','1','jobadmin','2016/05/07','jobadmin','2016/05/12');
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'9c64a7aa058846d6a83508f7fe26124a','TripleOrgService_fortune','TripleOrgService','fortune','三网组织机构信息同步','com.creditharmony.fortunejob.facade.TripleOrgService','tripleOrg','40 0/2 * * * ?','1','jobadmin','2016/05/07','jobadmin','2016/05/12');
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'33e21463989a42f3945c44386a0dbada','FortuneSmsBatchSendService_fortune','FortuneSmsBatchSendService','fortune','财富短信实时发送处理','com.creditharmony.fortunejob.facade.FortuneSmsBatchSendService','fortuneSmsBatchSend','10 0/5 8-21 * * ?','1','jobadmin','2016/05/07','jobadmin','2016/05/12');
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'214f9eb99d69436f82ddcd5526f352c7','TripleCustomerService_fortune','TripleCustomerService','fortune','三网客户信息发送失败重新发送','com.creditharmony.fortunejob.facade.TripleCustomerService','tripleCustomer','10 0/2 * * * ?','1','jobadmin','2016/05/07','jobadmin','2016/05/12');
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'd624fd4930664eadbab91e769b649ff2','EnddayReleaseCreditorService_fortune','EnddayReleaseCreditorService','fortune','出借到期释放债权','com.creditharmony.fortunejob.facade.EnddayReleaseCreditorService','run','15 0/1 * * * ? 2025','1','jobadmin','2016/05/13',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'4799dd771aa94982a635e3cbbaf9d039','YmyService_fortune','YmyService','fortune','月满盈批处理','com.creditharmony.fortunejob.facade.YmyService','run','0 10 0 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'd71979772bb94175bd87c9accb72ffd8','AutoMatchingJob_fortune','AutoMatchingJob','fortune','债权匹配--自动匹配','com.creditharmony.fortunejob.fortune.matching.job.AutoMatchingJob','start','0 0 23 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'0489a896e83441e39971ee449cf0caa1','BackInterest2Service_fortune','BackInterest2Service','fortune','回息-信和宝（12月回息）、信和宝A、信和宝C每日跑批','com.creditharmony.fortunejob.facade.BackInterest2Service','run','0 30 21 * * ?','1','jobadmin','2016/05/07',NULL,NULL);
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'25577c98917e46eb90d209cdfcc85746','CreditorMatchingService_fortune','CreditorMatchingService','fortune','债权匹配结算日批处理','com.creditharmony.fortunejob.facade.CreditorMatchingService','run','0 0 23 * * ?','1','jobadmin','2016/05/07','jobadmin','2016/05/07');
INSERT INTO tz.t_gl_qrtz_job (schedid,sched_name,job_name,job_group,description,job_class_name,job_method_name,cron_expression,status,createby,createdate,modifyby,modifydate) VALUES (
'44c3574aac3e4659a9c9562c618bca4b','BackInterestService_fortune','BackInterestService','fortune','月息通，信和月增，月邮赢结算日跑批','com.creditharmony.fortunejob.facade.BackInterestService','run','0 0 22 * * ?','1','jobadmin','2016/05/07','jobadmin','2016/05/07');

