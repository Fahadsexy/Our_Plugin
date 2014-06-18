#output "output.txt"

#set choose = "big"
#select choose
	#option big|huge
		#adapt: "a.vcl"
			#insert-before break_a
inserting before the break point
			#endinsert
			#insert break_a
inserting inside the break point
			#endinsert
			#insert-after break_a
inserting after the break point
			#endinsert
		#endadapt
	#endoption
	#option small
		#adapt "b.vcl"
	#endoption
	#otherwise
		the variable should be either big or small now is ?@choose?
	#endotherwise
#endselect