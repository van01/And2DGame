//
//  HelloDetailViewController.h
//  DrinkMixer
//
//  Created by incross on 2014. 1. 9..
//  Copyright (c) 2014년 incross. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HelloDetailViewController : UIViewController
{
    IBOutlet UILabel* ingredientsTextView;
    IBOutlet UILabel* directionsTextView;
    IBOutlet UITextField* nameTextField;
}

@property (retain, nonatomic) UILabel* ingredientsTextView;
@property (retain, nonatomic) UILabel* directionsTextView;
@property (retain, nonatomic) UITextField* nameTextField;

@property (strong, nonatomic) id detailItem;

@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;
@end
