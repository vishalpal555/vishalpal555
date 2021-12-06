import pygame
import time
import random
from pygame import mixer


from pygame.constants import K_RIGHT, KEYUP

pygame.init() #gui
pygame.mixer.init()  #sound

clock = pygame.time.Clock()

screen = pygame.display.set_mode((840,600))
pygame.display.set_caption("My first Pygame")
# icon = pygame.image.load("heli_left.png") # set icon
# pygame.display.set_icon(icon) #display icon
intro_font_size = 40
intro_font = pygame.font.Font("freesansbold.ttf", intro_font_size)
font = pygame.font.Font("freesansbold.ttf", 20)
text_x = 670
text_y = 10

blast_sound = pygame.mixer.Sound("blastSound.mp3")
laser_sound = pygame.mixer.Sound("laserSound.wav")

score = 0
missiles_fired = 0

spaceShip_image = pygame.image.load("space-ship.png")
#spaceShip_image =pygame.transform.scale(spaceShip_image, (64,64)) #scaling
spaceShip_imageX = (840-64) // 2
spaceShip_imageY = 520
dx = 0

red_enemy_img = pygame.image.load("red_enemy.png")
skull_img = pygame.image.load("skull.png")
virus_img = pygame.image.load("virus.png")
enemy_img = []
enemy_x = []
enemy_y = []
enemy_dy = []
enemy_dx = []
enemy_numbers = 6
for i in range(enemy_numbers):
    enemy_img.append(random.choice([red_enemy_img, skull_img, virus_img]))
    enemy_x.append(random.randint(10,500))
    enemy_y.append(random.randint(40,200))
    enemy_dy.append(40)
    enemy_dx.append(random.randint(1,3))

background_img = pygame.image.load("background-space.png")
background_img = pygame.transform.scale(background_img, (840,600))

missile_img = pygame.image.load("missile.png")
missile_x = 0
missile_y = 520-50
missile_dy = 7
missile_state = "ready"

blast_img = pygame.image.load("blast.png")

def player(x,y):
    screen.blit(spaceShip_image, (x,y)) #drawing on screen

def enemy(x,y,i):
    screen.blit(enemy_img[i], (x,y))

def fire_missile(x,y):
    global missile_state
    missile_state = "fire"
    screen.blit(missile_img,(x+22,y))

def isCollision(x_enemy, y_enemy, x_missile, y_missile):
    distance = ( ((x_enemy-x_missile)**2) + ((y_enemy-y_missile)**2) ) ** (1/2)
    if distance < 28:
        return True
    else:
        return False

def show_score(x,y):
    text_score = font.render("Score: " + str(score), True, (255,255,255))
    try:
        text_accuracy = font.render("Accuracy: "+ str(int((score/missiles_fired)*100)) +"%", True, (255,255,255))
    except ZeroDivisionError:
        text_accuracy = font.render("Accuracy: " + str(0) + "%", True, (255,255,255))
    screen.blit(text_score, (x,y))
    screen.blit(text_accuracy, (x,y+40))

class intro_video:
    def start_game(screen_width,screen_height):
        x = screen_width//2 - 120
        y = screen_height//2 - 50
        start_game_text = intro_font.render("Start Game",True,(255,255,255))
        screen.blit(start_game_text, (x,y))
        return start_game_text.get_rect(topleft=(x,y))
    
game_over_wait = 1     
animation_x = 1
animation_y = 1
click_x = None
click_y = None
mode="intro"
running = True
while running:

    screen.blit(background_img, (0,0)) #background Image

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        
        if mode == "intro":
            if event.type == pygame.MOUSEBUTTONDOWN:
                if pygame.mouse.get_pressed()[0]:

                    click_x, click_y = pygame.mouse.get_pos()
                    print(click_x,click_y)
                    
        
        if mode == "game":
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RIGHT:
                    dx = 4
                if event.key == pygame.K_LEFT:
                    dx = -4     
                if event.key == pygame.K_SPACE:
                    if missile_state == "ready":
                        missiles_fired += 1
                        pygame.mixer.Sound.play(laser_sound)
                        missile_x = spaceShip_imageX
                        fire_missile(missile_x, missile_y)
            if event.type == KEYUP:
                if event.key == pygame.K_RIGHT or event.key == pygame.K_LEFT:
                    dx = 0
    if mode == "intro":
        player(animation_x, animation_y)
        if animation_y < 510:
            animation_x += 3.5
            animation_y *= 1.06

        else:
            start_game_text = intro_video.start_game(840,600)
            if click_y and click_x:
                if start_game_text.collidepoint(click_x,click_y):
                    print("starting game")
                    click_x = None
                    click_y = None
                    mode = "game"
    if mode == "game-over":
        game_over_text = intro_font.render("Game Over",True,(255,255,255))
        screen.blit(game_over_text,((840//2-140),(600//2-50)))
        game_over_wait += 1
        if game_over_wait > 180:
            game_over_wait = 0
            mode = "intro"

    if mode == "game":
        if missile_state == "fire":
            fire_missile(missile_x, missile_y)
            if missile_y > -49:
                missile_y -= missile_dy
            else:
                missile_state = "ready"
                missile_y = 520-50
            

        spaceShip_imageX += dx
        if spaceShip_imageX<10:
            spaceShip_imageX = 10
        if spaceShip_imageX>774:
            spaceShip_imageX = 774
        
        for i in range(enemy_numbers):
            if enemy_y[i] > 520-50:
                for j in range(enemy_numbers):
                    enemy_y[j] = 2000
                
                mode = "game-over"
                game_over_wait = 1
                break

            enemy_x[i] += enemy_dx[i]

            if enemy_x[i]<5:
                enemy_dx[i] = random.randint(1,3)
                enemy_y[i] += enemy_dy[i]
            if enemy_x[i]>770:
                enemy_dx[i] = random.randint(-2,0)
                enemy_y[i] += enemy_dy[i]
        
            collision = isCollision(x_enemy=enemy_x[i], y_enemy=enemy_y[i], x_missile=missile_x, y_missile=missile_y)

            if collision:
                screen.blit(blast_img,(enemy_x[i],enemy_y[i]))
                pygame.mixer.Sound.play(blast_sound)
                missile_y = 520-50
                missile_state = "ready"
                score += 1
                
                enemy_x[i] = random.randint(10,500)
                enemy_y[i] = random.randint(40,200)

            enemy(enemy_x[i], enemy_y[i], i)

        player(spaceShip_imageX,spaceShip_imageY)
    show_score(text_x,text_y)
    pygame.display.update()
    clock.tick(60)