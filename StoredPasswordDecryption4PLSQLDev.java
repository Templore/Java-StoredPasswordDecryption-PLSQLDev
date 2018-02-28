/**
 *	Reference article: https://adamcaudill.com/2016/02/02/plsql-developer-nonexistent-encryption/
 *	Reference project: https://github.com/adamcaudill/PLSQLDevPass
 *
 *	My project: https://github.com/Templore/Java-StoredPasswordDecryption-PLSQLDev
 *	Created by Templore on 2018.02.27.
 *
 *	PLSQL Developer 会将[保存的]密码存储在一个名为 `user.prefs` 的文件中，该文件可用记事本打开。
 *	`user.prefs` 位于安装目录下的 `Preferences\<username>\` 或者  
 *	`C:\Users\<username>\AppData\Roaming\PLSQL Developer\Preferences\<username>\` 或者 
 *	`C:\Users\<username>\AppData\Roaming\PLSQL Developer\PLS-Recovery\*.cfg` 中。
 *
 *	一般数据库的用户名、密码等信息存储在 `[LogonHistory]` 中, 一行是一条记录, 如下示例:
 *		273645624572423045763066456443024120413041724566408044424900419043284194407643904160
 *	记录格式一般是 `<username>/<password>@<server>`。
 *
 *	在 `[CurrentConnections]` 中也会存错[保存的]登录信息, 一行是一条记录, 但是记录格式是 `<username>,<password>,<server>,,,`。
 *
 *	本 Demo 完全参考了上面提及的博客文章及代码，只不过原作者是用 C# 示例，而我持研究学习之目的，以 JAVA 来示例。
 *	感兴趣的朋友请阅读上面提及的文章。
 */

package com.templore;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		String str = "273645624572423045763066456443024120413041724566408044424900419043284194407643904160";
		
		for (int i = 0; i < str.length(); i += 4) {
			values.add(Integer.valueOf(str.substring(i, 4 + i)));
		}
		
		Integer key = values.get(0);
		values.remove(0);
		
		for (int i = 0; i < values.size(); i++) {
			
			int val = values.get(i) - 1000;
			int mask = key + (10 * (i + 1));
			
			System.out.print((char)((val ^ mask) >> 4));
		}
	}
}