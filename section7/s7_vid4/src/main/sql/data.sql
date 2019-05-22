-- Create Accounts
call createAccount('user1', 'User1', 'Last1', 'user1@example.com', @user1);
call createAccount('user2', 'User2', 'Last2', 'user2@example.com', @user2);
call createAccount('user3', 'User3', 'Last3', 'user3@example.com', @user3);

-- Create Permissions
call createPermission('ROLE_READ_PAGES');

-- Create Roles
call createRole('ROLE_USER', @role_user);
call roleHasPermission(@role_user, 'ROLE_READ_PAGES');

-- Provide Roles to Accounts
call accountHasRole(@user1, @role_user);
call accountHasRole(@user2, @role_user);
call accountHasRole(@user3, @role_user);


-- Create possessions 

call createAclClass('com.packt.springsecurity.backend.model.Possession', @possession_class);

call createPossession('Possession 1', @user1, @possession);
call createPossession('Possession 2', @user2, @possession);
call createPossession('Possession 3', @user2, @possession);
call createPossession('Possession 4', @user3, @possession);
call createPossession('Possession 5', @user3, @possession);
call createPossession('Possession 6', @user3, @possession);

-- call givePermission(1, @user2);