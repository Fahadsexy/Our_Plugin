#output "output.txt"
#adapt: "a.vcl"

#insert varbreak
	#var a = 4345
#endinsert

#insert iterbreak
	#iter var1 = "1c", var2 = "2c", var4 = 12, a = 4
	#iter var1 = "1c", var2 = "2c", var4 = 12, a = 213
#endinsert

#endadapt