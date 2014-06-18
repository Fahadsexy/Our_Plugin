VCL (Variation Configuration Language) version 1.1.4
---------------------------------------------------------

New features described here: https://sourceforge.net/p/vclang/discussion/features/

Command line usage: VCL_1.1.4 <inputfile> (from the VCL's folder)
example: "VCL_1.1.4 input.vcl"

For using VCL 1.1.4 you will need an installed JRE on the computer.
You can get it from here: http://java.com/en/download/index.jsp

Content of the folder:

1. VCL_1.1.4.jar - Executable jar, the VCL processor
2. VCL_1.1.4.bat - A simple script for easier usage of the jar file
3. Notes.txt - Describes bigger changes from XVCL, may be important for those, who is familiar with XVCL.

You can find explanation of commands, examples and other descriptions on the VCL website below.

More information: http://vcl.comp.nus.edu.sg
Discussion about new and planned features: http://sourceforge.net/p/vclang/discussion/features/

------------------------------------------------------------------------------------------------------

Remark: From VCL 1.1.4 string-expressions  are not going to be evaluated in the text content.

i.e. 
	input:
	#set A = 5
		"t1"?@A?"t2"
	output: 
	"t1"5"t2" // before VCL 1.1.4 -> t15t2 

We found that, the string-expressions in the textual content are never
or very rarely used. Having string-expressions allowed in textual content however introduced the need
of escaping every “ double quote character in the text which in programming languages is very frequently present.
For these reasons, by default in textual content the processor won’t evaluate string expressions in textual content,
just at definitions and commands which take expressions as input. So we don’t need to escape double quote characters
in the instrumented code String expressions can be still used in textual content easily. i.e.:
	
	input:
	#set A = 5
	#set tAt = "t1"?@A?"t2"
	?@tAt?
	output: 
	t15t2

For compatibility reasons, \” (escaped double quote character) will still be accepted and the output will be “ (unescaped double quote character).
