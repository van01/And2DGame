//
//  DrinkDetailControllerViewViewController.m
//  DrinkMixer
//
//  Created by incross on 2014. 1. 12..
//  Copyright (c) 2014년 incross. All rights reserved.
//

#import "DrinkDetailViewController.h"

@interface DrinkDetailControllerViewViewController ()

@end

@implementation DrinkDetailControllerViewViewController

@synthesize nameTextField;
@synthesize ingredientsTextView;
@synthesize directionsTextView;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
