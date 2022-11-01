import React, {useEffect, useState} from "react";
import './ProfileData.css';
import {
    Heading,
    Avatar,
    Box,
    Center,
    Text,
    Stack,
    Badge, Button, useColorModeValue,
} from '@chakra-ui/react';
import {Link} from "react-router-dom";

export function ProfileData() {
    const boxColor = useColorModeValue('white', 'gray.900');
    const textColor = useColorModeValue('gray.700', 'gray.400');
    const badgeColor = useColorModeValue('gray.50', 'gray.800');
    const [userObject, setUsersObject] = useState({});
    const [filtersEnabled, setFiltersEnabled] = useState({});
    const [userMemberships, setUserMemberships] = useState([]);

    useEffect(() => {
        handleGetUserObject();
        handleGetFilterState();
        handleGetUserMemberships();
    }, []);

    const handleGetUserObject = () => {
        fetch(`http://localhost:8080/api/appuser`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setUsersObject(data));
    }


    const reloadPage = () => {
        const reloadCount = sessionStorage.getItem('reloadCount');
        if (reloadCount < 2) {
            sessionStorage.setItem('reloadCount', String(reloadCount + 1));
            window.location.reload();
        } else {
            sessionStorage.removeItem('reloadCount');
        }
    }

    const handleGetFilterState = () => {
        fetch(`http://localhost:8080/api/filter/filtersEnabled`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setFiltersEnabled(data));
    }

    const handleEnableFilters = () => {
        fetch('http://localhost:8080/api/filter/enableFilters', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(data => console.log("Enable"));
        handleGetFilterState();
        reloadPage();
    }

    const handleDisableFilters = () => {
        fetch('http://localhost:8080/api/filter/disableFilters', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(data => console.log("Disable"));
        handleGetFilterState();
        reloadPage();
    }

    const handleGetUserMemberships = () => {
        fetch(`http://localhost:8080/api/membership`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setUserMemberships(data));
    }

    const renderFilterButtons = () => {
        if (filtersEnabled) {
            return (
                <Stack mt={8} direction={'row'} spacing={4}>
                    <Button
                        as={Link}
                        to={'/filters'}
                        flex={1}
                        fontSize={'sm'}
                        style={{textDecoration: 'none'}}
                        rounded={'full'}
                        _hover={{
                            bg: badgeColor,
                        }}
                        _focus={{
                            bg: badgeColor,
                        }}>
                        Szűrők beállítása
                    </Button>
                    <Button
                        flex={1}
                        fontSize={'sm'}
                        rounded={'full'}
                        bg={'red.400'}
                        color={'white'}
                        onClick={handleDisableFilters}
                        boxShadow={
                            '0px 1px 25px -5px rgb(255 0 0 / 40%), 0 10px 10px -5px rgb(255 0 0 / 35%)'
                        }
                        _hover={{
                            bg: 'red.500',
                        }}
                        _focus={{
                            bg: 'red.500',
                        }}>
                        Szűrők kikapcsolása
                    </Button>
                </Stack>
            )
        } else {
            return (
                <Stack mt={8} direction={'row'} spacing={4}>
                    <Button
                        flex={1}
                        fontSize={'sm'}
                        rounded={'full'}
                        bg={'green.400'}
                        color={'white'}
                        onClick={handleEnableFilters}
                        boxShadow={
                            '0px 1px 25px -5px rgb(0 255 0 / 40%), 0 10px 10px -5px rgb(0 255 0 / 35%)'
                        }
                        _hover={{
                            bg: 'green.500',
                        }}
                        _focus={{
                            bg: 'green.500',
                        }}>
                        Szűrők bekapcsolása
                    </Button>
                </Stack>
            )
        }
    }


    const renderUserCircles = () => {
        console.log(userMemberships);
        if (userMemberships !== undefined) {
            return (<Stack align={'center'} justify={'center'} >

                {userMemberships.map((membership) => {
                    return (
                        <Badge
                            bg={badgeColor}
                            fontWeight={'400'}>
                            <Box color={textColor}>{membership.circleName}</Box>
                        </Badge>
                    )
                })}
            </Stack>);
        }
    }

    const render = () =>  {
        return (
            <Center py={6}>
                <Box
                    maxW={'500px'}
                    w={'full'}
                    bg={boxColor}
                    boxShadow={'2xl'}
                    rounded={'lg'}
                    p={6}
                    textAlign={'center'}>

                    <Avatar
                        size={'xl'}
                        src={
                            'https://t3.ftcdn.net/jpg/00/64/67/80/360_F_64678017_zUpiZFjj04cnLri7oADnyMH0XBYyQghG.jpg'
                        }
                        alt={'Avatar Alt'}
                        mb={4}
                        pos={'relative'}
                    />
                    <Heading fontSize={'2xl'} fontFamily={'body'} color={textColor}>
                        {userObject.name}
                    </Heading>
                    <Text fontWeight={600} color={textColor} mb={4}>
                        {userObject.email}
                    </Text>
                    <Box>
                        {renderUserCircles()}
                    </Box>

                    {renderFilterButtons()}
                </Box>
            </Center>
        );
    }

    return render();
}

export default ProfileData;

