import {
    Box,
    Flex,
    Button,
    useColorModeValue,
    Stack,
    useColorMode,
    Heading,
} from '@chakra-ui/react';
import {InfoIcon, MoonIcon, PlusSquareIcon, SunIcon} from '@chakra-ui/icons';
import LoginControl from "../login/LoginControl";
import {Link} from "react-router-dom";

export default function AppNavbar() {
    const {colorMode, toggleColorMode} = useColorMode();

    return (
        <>
            <Box bg={useColorModeValue('gray.100', 'gray.900')} px={4}>
                <Flex h={16} alignItems={'center'} justifyContent={'space-between'}>
                    <Flex alignItems={'center'}>
                        <Stack direction={'row'} spacing={2}>
                            <Heading as={Link} to={'/'}
                                textShadow='3px 3px #808080'>PROGRAM.SCH</Heading>

                            <Button as={Link} to={'/event/new'} variant={'ghost'} rightIcon={<PlusSquareIcon/>}
                                style={{textDecoration: 'none'}}>Új program</Button>

                            <Button as={Link} to={"http://localhost:8080/swagger-ui/index.html"} style={{textDecoration: 'none'}} variant={'ghost'} rightIcon={<InfoIcon/>}>
                                API</Button>
                        </Stack>
                    </Flex>
                    <Flex alignItems={'center'}>
                        <Stack direction={'row'} spacing={2}>
                            <Button onClick={toggleColorMode}>
                                {colorMode === 'light' ? <MoonIcon/> : <SunIcon/>}
                            </Button>
                            <LoginControl/>
                        </Stack>
                    </Flex>
                </Flex>
            </Box>
        </>
    );
}
