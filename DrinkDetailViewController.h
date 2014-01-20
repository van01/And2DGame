//
//  DrinkDetailControllerViewViewController.h
//  DrinkMixer
//
//  Created by incross on 2014. 1. 12..
//  Copyright (c) 2014년 incross. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DrinkDetailControllerViewViewController : UIViewController
{
    IBOutlet UITextField* nameTextField;
    IBOutlet UITextView* ingredientsTextView;
    IBOutlet UITextView* directionsTextView;
}

@property (nonatomic, retain) UITextField* nameTextField;
@property (nonatomic, retain) UITextView* ingredientsTextView;
@property (nonatomic, retain) UITextView* directionsTextView;

@end
