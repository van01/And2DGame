//
//  HelloDetailViewController.h
//  DrinkMixer
//
//  Created by incross on 2014. 1. 9..
//  Copyright (c) 2014년 incross. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HelloDetailViewController : UIViewController

@property (strong, nonatomic) id detailItem;

@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;
@end
