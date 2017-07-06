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

