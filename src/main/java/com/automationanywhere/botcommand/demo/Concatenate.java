/*
 * Copyright (c) 2019 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */
/**
 * 
 */
package com.automationanywhere.botcommand.demo;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;

/**
 * @author Raj Singh Sisodia
 *
 */
@BotCommand
@CommandPkg(label="Concatenate", name="concatenate", description="Concatenates two strings", icon="pkg.svg",
node_label="Concatenate {{firstString}} with {{secondString}}",
return_type=STRING, return_label="Assign the output to variable", return_required=true)
public class Concatenate {
	
	private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");
	
	@Execute
	public Value<String> action(@Idx(index = "1", type = TEXT) @Pkg(label = "First string", default_value_type = STRING) @NotEmpty String firstString, 
			@Idx(index = "2", type = TEXT) @Pkg(label = "Second string") @NotEmpty String secondString) {
		
		if("".equals(firstString.trim()))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "firstString"));
		
		if("".equals(secondString.trim()))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "secondString"));
		
		String result = firstString + secondString;
		
		return new StringValue(result);
		
	}
	

}
