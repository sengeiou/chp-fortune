--生产IP 需修改

	sql -d gldb -h 10.180.100.10 -p 1975 -U gl -c "\copy gl.t_gl_org  to STDIN" >  t_gl_org.CSV
	sql -d tzdb -h 10.180.100.4 -p 1975 -U tz -c "\copy tz.t_gl_org from STDIN" < t_gl_org.CSV

	sql -d gldb -h 10.180.100.10 -p 1975 -U gl -c "\copy gl.t_gl_user  to STDIN" >  t_gl_user.CSV
	sql -d tzdb -h 10.180.100.4 -p 1975 -U tz -c "\copy tz.t_gl_user from STDIN" < t_gl_user.CSV


	sql -d gldb -h 10.180.100.10 -p 1975 -U gl -c "\copy gl.t_gl_role  to STDIN" >  t_gl_role.CSV
	sql -d tzdb -h 10.180.100.4 -p 1975 -U tz -c "\copy tz.t_gl_role from STDIN" < t_gl_role.CSV


	sql -d gldb -h 10.180.100.10 -p 1975 -U gl -c "\copy gl.t_gl_user_role_org  to STDIN" >  t_gl_user_role_org.CSV
	sql -d tzdb -h 10.180.100.4 -p 1975 -U tz -c "\copy tz.t_gl_user_role_org from STDIN" < t_gl_user_role_org.CSV

	sql -d gldb -h 10.180.100.10 -p 1975 -U gl -c "\copy gl.t_gl_dict  to STDIN" >  t_gl_dict.CSV
	sql -d tzdb -h 10.180.100.4 -p 1975 -U tz -c "\copy tz.t_gl_dict from STDIN" < t_gl_dict.CSV
	
	sql -d gldb -h 10.180.100.10 -p 1975 -U gl -c "\copy gl.t_gl_province_city  to STDIN" >  t_gl_province_city.CSV
	sql -d tzdb -h 10.180.100.4 -p 1975 -U tz -c "\copy tz.t_gl_province_city from STDIN" < t_gl_province_city.CSV