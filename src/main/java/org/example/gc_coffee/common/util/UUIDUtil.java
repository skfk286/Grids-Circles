package org.example.gc_coffee.common.util;

import com.github.f4b6a3.uuid.UuidCreator;
import org.slf4j.Logger;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * MySQL은 클러스터드 인덱스로 되어 있어 순차적인 인덱스에 최적화되어 있기 때문에, UUID는 성능적인 측면에서 비효율적입니다.
 */
public class UUIDUtil {
    private static final Logger logger = LoggerUtil.getLogger();

    // UUIDv1 - 시간 기반 UUID 생성 (B-tree 같은 인덱스 구조에서 페이지 분할이 적어 성능이 향상)
    public static UUID generateUUIDv1() {
        return generateReorderedUUIDv1();
    }

    private static UUID generateReorderedUUIDv1() {
        UUID uuid = UuidCreator.getTimeBased();
        logger.info("order origin UUID: {}", uuid);
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);

        // UUID를 128비트(16바이트) 배열로 변환
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());

        byte[] uuidBytes = buffer.array();

        // UUID v1을 재배열하여 3 - 2 - 1 - 4 - 5 순서로 변경
        byte[] reorderedBytes = new byte[16];

        // 순차적인 타임스탬프의 하위 비트 (3번째 위치)
        System.arraycopy(uuidBytes, 4, reorderedBytes, 0, 4);

        // 타임스탬프의 중간 비트 (2번째 위치)
        System.arraycopy(uuidBytes, 2, reorderedBytes, 4, 2);

        // 타임스탬프의 상위 비트 (1번째 위치)
        System.arraycopy(uuidBytes, 0, reorderedBytes, 6, 2);

        // 클럭 시퀀스와 버전 정보 (4번째 위치)
        System.arraycopy(uuidBytes, 8, reorderedBytes, 8, 2);

        // 노드 정보 (5번째 위치)
        System.arraycopy(uuidBytes, 10, reorderedBytes, 10, 6);

        // 재배열된 바이트 배열을 다시 UUID로 변환
        ByteBuffer reorderedBuffer = ByteBuffer.wrap(reorderedBytes);
        long mostSigBits = reorderedBuffer.getLong();
        long leastSigBits = reorderedBuffer.getLong();

        return new UUID(mostSigBits, leastSigBits);
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

