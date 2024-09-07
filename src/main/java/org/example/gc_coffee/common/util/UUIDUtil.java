package org.example.gc_coffee.common.util;

import com.github.f4b6a3.uuid.UuidCreator;
import java.util.UUID;

public class UUIDUtil {

    // UUIDv1 - 시간 기반 UUID 생성
    public static UUID generateUUIDv1() {
        return UuidCreator.getTimeBased();
    }

    // UUIDv6 - 시간 순서대로 정렬 가능한 UUID 생성
    public static UUID generateUUIDv6() {
        return UuidCreator.getTimeOrdered();
    }

    // UUIDv4 - 랜덤 기반 UUID 생성
    public static UUID generateUUIDv4() {
        return UUID.randomUUID(); // java.util.UUID에서 제공하는 랜덤 UUID
    }

    // UUIDv3 - 이름 기반 UUID (MD5 해싱)
    public static UUID generateUUIDv3(String namespace, String name) {
        UUID namespaceUUID = UUID.fromString(namespace);
        return UuidCreator.getNameBasedMd5(namespaceUUID, name);
    }

    // UUIDv5 - 이름 기반 UUID (SHA1 해싱)
    public static UUID generateUUIDv5(String namespace, String name) {
        UUID namespaceUUID = UUID.fromString(namespace);
        return UuidCreator.getNameBasedSha1(namespaceUUID, name);
    }

    // UUID 생성 여부 검증 (유효성 체크)
    public static boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("UUIDv1 : " + UUIDUtil.generateUUIDv1());
        System.out.println("UUIDv3 : " + UUIDUtil.generateUUIDv4());
        System.out.println("UUIDv5 : " + UUIDUtil.generateUUIDv6());
    }
}

