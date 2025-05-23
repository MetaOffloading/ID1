//The SequenceHandler is the piece of code that defines the sequence of events
//that constitute the experiment.
//
//SequenceHandler.Next() will run the next step in the sequence.
//
//We can also switch between the main sequence of events and a subsequence
//using the SequenceHandler.SetLoop command. This takes two inputs:
//The first sets which loop we are in. 0 is the main loop. 1 is the first
//subloop. 2 is the second subloop, and so on.
//
//The second input is a Boolean. If this is set to true we initialise the 
//position so that the sequence will start from the beginning. If it is
//set to false, we will continue from whichever position we were currently in.
//
//So SequenceHandler.SetLoop(1,true) will switch to the first subloop,
//starting from the beginning.
//
//SequenceHandler.SetLoop(0,false) will switch to the main loop,
//continuing from where we left off.

package com.sam.webtasks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.sam.webtasks.basictools.CheckIdExists;
import com.sam.webtasks.basictools.CheckScreenSize;
import com.sam.webtasks.basictools.ClickPage;
import com.sam.webtasks.basictools.Consent;
import com.sam.webtasks.basictools.Counterbalance;
import com.sam.webtasks.basictools.InfoSheet;
import com.sam.webtasks.basictools.Initialise;
import com.sam.webtasks.basictools.Names;
import com.sam.webtasks.basictools.PHP;
import com.sam.webtasks.basictools.ProgressBar;
import com.sam.webtasks.basictools.Slider;
import com.sam.webtasks.basictools.TimeStamp;
import com.sam.webtasks.iotask1.IOtask1Block;
import com.sam.webtasks.iotask1.IOtask1BlockContext;
import com.sam.webtasks.iotask1.IOtask1DisplayParams;
import com.sam.webtasks.iotask1.IOtask1InitialiseTrial;
import com.sam.webtasks.iotask1.IOtask1RunTrial;



public class SequenceHandler {
	public static void Next() {	
		// move forward one step in whichever loop we are now in
		sequencePosition.set(whichLoop, sequencePosition.get(whichLoop) + 1);

		switch (whichLoop) {
		case 0: // MAIN LOOP
			switch (sequencePosition.get(0)) {
			/***********************************************************************
			 * The code here defines the main sequence of events in the experiment *
			 ********************************************************************/			
			case 1:
				ClickPage.Run(Instructions.Get(0), "Next");
				break;
			case 2:
				//practice 1: 10 circles, no targets
				IOtask1Block block1 = new IOtask1Block();
				block1.blockNum = 1;
				block1.nTrials = 1;
				block1.nTargets = 0;
				block1.askArithmetic = false;
				block1.offloadCondition = Names.REMINDERS_NOTALLOWED;		
				block1.Run();
				break;
			case 3:
				ClickPage.Run(Instructions.Get(1), "Next");
				break;
			case 4:
				//practice 2: 10 circles, 1 target
				IOtask1Block block2 = new IOtask1Block();
				block2.blockNum = 2;
				block2.nTrials = 1;
				block2.nTargets = 1;
				block2.askArithmetic = false;
				block2.offloadCondition = Names.REMINDERS_NOTALLOWED;		
				block2.Run();				
				break;
			case 5:
				ClickPage.Run(Instructions.Get(2),  "Next");
				break;
			case 6:
				//practice 3: 10 circles, 2 targets
				IOtask1Block block3 = new IOtask1Block();
				block3.blockNum = 3;
				block3.nTrials = 1;
				block3.nTargets = 2;
				block3.askArithmetic = false;
				block3.offloadCondition = Names.REMINDERS_NOTALLOWED;		
				block3.Run();				
				break;
			case 7:
				ClickPage.Run(Instructions.Get(3), "Next");
				break;
			case 8:
				//practice 3: 10 circles, 3 targets
				IOtask1Block block4 = new IOtask1Block();
				block4.blockNum = 4;
				block4.nTrials = 1;
				block4.nTargets = 3;
				block4.askArithmetic = false;
				block4.offloadCondition = Names.REMINDERS_NOTALLOWED;		
				block4.Run();				
				break;
			case 9:
				ClickPage.Run(Instructions.Get(5), "Next");
				break;
			case 10:
				//practice 5: 10 circles, 2 targets, optional reminders
				IOtask1Block block6 = new IOtask1Block();
				block6.blockNum = 6;
				block6.nTrials = 1;
				block6.nTargets = 2;
				block6.askArithmetic = false;
				block6.offloadCondition = Names.REMINDERS_OPTIONAL;	
				block6.Run();
				break;
			case 11:
				//instructions phase 1
				
				//Window.alert("test");

				if (Counterbalance.getFactorLevel("conditionOrder") == ExtraNames.OFFLOAD_SECOND){
					ClickPage.Run(Instructions.Get(7), "Next");
				}
				if (Counterbalance.getFactorLevel("conditionOrder") == ExtraNames.OFFLOAD_FIRST){
					ClickPage.Run(Instructions.Get(6), "Next");
				}
				
				break;				
			case 12:
				//phase 1
				IOtask1Block block7 = new IOtask1Block();
				block7.blockNum = 7;
				block7.nTrials = 9;
				block7.targetList.add(1);
				block7.targetList.add(2);
				block7.targetList.add(3);
				block7.targetList.add(1);
				block7.targetList.add(2);
				block7.targetList.add(3);
				block7.targetList.add(1);
				block7.targetList.add(2);
				block7.targetList.add(3);				
				block7.askArithmetic = false;
				if (Counterbalance.getFactorLevel("conditionOrder") == ExtraNames.OFFLOAD_SECOND) {
					block7.offloadCondition = Names.REMINDERS_NOTALLOWED;}
				if (Counterbalance.getFactorLevel("conditionOrder") == ExtraNames.OFFLOAD_FIRST) {
					block7.offloadCondition = Names.REMINDERS_OPTIONAL;}
				block7.Run();
				break;
			case 13:
				//instructions phase 2
				if (Counterbalance.getFactorLevel("conditionOrder") == ExtraNames.OFFLOAD_SECOND) {
					ClickPage.Run(Instructions.Get(9), "Next");
				}
				
				if (Counterbalance.getFactorLevel("conditionOrder") == ExtraNames.OFFLOAD_FIRST) {
					ClickPage.Run(Instructions.Get(8), "Next");
				}
				break;
			case 14:
				//phase 2
				IOtask1Block block8 = new IOtask1Block();
				block8.blockNum = 8;
				block8.nTrials = 9;
				block8.targetList.add(1);
				block8.targetList.add(2);
				block8.targetList.add(3);
				block8.targetList.add(1);
				block8.targetList.add(2);
				block8.targetList.add(3);
				block8.targetList.add(1);
				block8.targetList.add(2);
				block8.targetList.add(3);
				block8.askArithmetic = true;
				if (Counterbalance.getFactorLevel("conditionOrder") == ExtraNames.OFFLOAD_SECOND) {
					block8.offloadCondition = Names.REMINDERS_OPTIONAL;}
				if (Counterbalance.getFactorLevel("conditionOrder") == ExtraNames.OFFLOAD_FIRST) {
					block8.offloadCondition = Names.REMINDERS_NOTALLOWED;}
				block8.Run();
				break;
			case 15:
				// log data and check that it saves
				String data = Counterbalance.getFactorLevel("conditionOrder") + ",";
				data = data + SessionInfo.gender + ",";
				data = data + SessionInfo.age + ",";
				data = data + TimeStamp.Now();

				PHP.logData("finish", data, true);
				break;
			case 16:
				// complete the experiment
				Finish.Run();
				break;
			}
			break;
		

		/********************************************/
		/* no need to edit the code below this line */
		/********************************************/

		case 1: // initialisation loop
			switch (sequencePosition.get(1)) {
			case 1:
				// initialise experiment settings
				Initialise.Run();
				break;
			case 2:
				// make sure that a participant ID has been registered.
				// If not, the participant may not have accepted the HIT
				CheckIdExists.Run();
				break;
			case 3:
				// check the status of this participant ID.
				// have they already accessed or completed the experiment? if so,
				// we may want to block them, depending on the setting of
				// SessionInfo.eligibility
				PHP.CheckStatus();
				break;
			case 4:
				// check whether this participant ID has been used to access a previous experiment
				PHP.CheckStatusPrevExp();
				break;
			case 5:
				// clear screen, now that initial checks have been done
				RootPanel.get().clear();

				// make sure the browser window is big enough
				CheckScreenSize.Run(SessionInfo.minScreenSize, SessionInfo.minScreenSize);
				break;
			case 6:
				if (SessionInfo.runInfoConsentPages) { 
					InfoSheet.Run(Instructions.InfoText());
				} else {
					SequenceHandler.Next();
				}
				break;
			case 7:
				if (SessionInfo.runInfoConsentPages) { 
					Consent.Run();
				} else {
					SequenceHandler.Next();
				}
				break;
			case 8:
				SequenceHandler.SetLoop(0, true); // switch to and initialise the main loop
				SequenceHandler.Next(); // start the loop
				break;
			}
			break;
			
		case 2: // IOtask1 loop
			switch (sequencePosition.get(2)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 2 *
			 * This runs a single trial of IOtask1                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask1Block block = IOtask1BlockContext.getContext();

				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(0, false);
				}

				SequenceHandler.Next();
				break;
			case 2:
				// now initialise trial and present instructions
				IOtask1InitialiseTrial.Run();
				break;
			case 3:
				// now run the trial
				IOtask1RunTrial.Run();
				break;
			case 4:
				// we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(2, true);
				SequenceHandler.Next();
				break;
				// TODO: mechanism to give post-trial feedback?
				
			}
		
		}
	}
	
	private static ArrayList<Integer> sequencePosition = new ArrayList<Integer>();
	private static int whichLoop;

	public static void SetLoop(int loop, Boolean init) {
		whichLoop = loop;

		while (whichLoop + 1 > sequencePosition.size()) { // is this a new loop?
			// if so, initialise the position in this loop to zero
			sequencePosition.add(0);
		}

		if (init) { // go the beginning of the sequence if init is true
			sequencePosition.set(whichLoop, 0);
		}
	}

	// set a new position
	public static void SetPosition(int newPosition) {
		sequencePosition.set(whichLoop, newPosition);
	}

	// get current loop
	public static int GetLoop() {
		return (whichLoop);
	}
	
	// get current position
	public static int GetPosition() {
		return (sequencePosition.get(whichLoop));
	}
}
